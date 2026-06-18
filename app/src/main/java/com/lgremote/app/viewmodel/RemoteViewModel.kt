package com.lgremote.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.lgremote.app.data.LgIrCode
import com.lgremote.app.data.LgIrDatabase
import com.lgremote.app.service.IrError
import com.lgremote.app.service.IrResult
import com.lgremote.app.service.IrService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * UI state for the remote control.
 */
data class RemoteUiState(
    /** Whether the device has IR hardware and supports LG frequency. */
    val isIrSupported: Boolean = false,
    /** Human-readable IR status message. */
    val irStatusMessage: String = "IR kontrol ediliyor...",
    /** The last successfully transmitted code (for button feedback). */
    val lastTransmitted: LgIrCode? = null,
    /** Error details if transmission failed. */
    val lastError: String? = null,
    /** Whether a transmission is technically in progress (briefly true). */
    val isTransmitting: Boolean = false
)

/**
 * ViewModel that owns the IR service lifecycle and exposes UI state
 * for all remote control screens.
 */
class RemoteViewModel(application: Application) : AndroidViewModel(application) {

    private val irService = IrService(application)

    private val _uiState = MutableStateFlow(RemoteUiState())
    val uiState: StateFlow<RemoteUiState> = _uiState.asStateFlow()

    init {
        checkIrSupport()
    }

    /**
     * Transmit an IR command and update UI state accordingly.
     */
    fun transmit(code: LgIrCode) {
        if (_uiState.value.isTransmitting) return

        _uiState.update { it.copy(isTransmitting = true, lastError = null) }

        viewModelScope.launch {
            try {
                val result = irService.transmit(code)
                when (result) {
                    is IrResult.Success -> {
                        _uiState.update {
                            it.copy(
                                lastTransmitted = result.code,
                                lastError = null,
                                isTransmitting = false
                            )
                        }
                    }
                    is IrResult.Error -> {
                        _uiState.update {
                            it.copy(
                                lastError = result.reason.userMessage +
                                    (result.message?.let { m -> ": $m" } ?: ""),
                                isTransmitting = false
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        lastError = "Beklenmeyen hata: ${e.message}",
                        isTransmitting = false
                    )
                }
            }
        }
    }

    /**
     * Transmit by command name (convenience).
     */
    fun transmitByName(name: String) {
        val code = LgIrDatabase.findByName(name) ?: run {
            _uiState.update { it.copy(lastError = "Komut bulunamadı: $name") }
            return
        }
        transmit(code)
    }

    /**
     * Clear any lingering error or success highlight.
     */
    fun clearFeedback() {
        _uiState.update { it.copy(lastTransmitted = null, lastError = null) }
    }

    // ───────────── Private ─────────────

    private fun checkIrSupport() {
        val supported = irService.hasIrBlaster && irService.supportsLgFrequency
        val message = when {
            !irService.hasIrBlaster -> IrError.NO_IR_EMITTER.userMessage
            !irService.supportsLgFrequency -> IrError.FREQUENCY_NOT_SUPPORTED.userMessage
            else -> "IR verici hazır ✓"
        }
        _uiState.update { it.copy(isIrSupported = supported, irStatusMessage = message) }
    }
}
