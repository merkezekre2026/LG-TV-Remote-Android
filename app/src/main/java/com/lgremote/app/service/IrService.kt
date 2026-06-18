package com.lgremote.app.service

import android.content.Context
import android.hardware.ConsumerIrManager
import com.lgremote.app.data.LgIrCode
import com.lgremote.app.data.LgIrDatabase
import com.lgremote.app.data.NecEncoder

/**
 * Service layer that wraps Android's [ConsumerIrManager] for LG TV infrared control.
 *
 * Handles:
 * - IR blaster availability detection
 * - NEC signal encoding and transmission
 * - Error states and feedback
 */
class IrService(context: Context) {

    private val irManager: ConsumerIrManager? =
        context.getSystemService(Context.CONSUMER_IR_SERVICE) as? ConsumerIrManager

    /** Whether the device has a built-in IR blaster. */
    val hasIrBlaster: Boolean
        get() = irManager?.hasIrEmitter() == true

    /**
     * Available IR carrier frequencies reported by the device.
     * LG TV requires 38 kHz; we check this is in the supported range.
     */
    val carrierFrequencies: List<ConsumerIrManager.CarrierFrequencyRange>
        get() = irManager?.carrierFrequencies?.toList() ?: emptyList()

    /**
     * Check if the device supports the 38 kHz carrier frequency required for LG TVs.
     */
    val supportsLgFrequency: Boolean
        get() {
            val freq = NecEncoder.CARRIER_FREQUENCY_HZ
            return carrierFrequencies.any { range ->
                freq >= range.minFrequency && freq <= range.maxFrequency
            }
        }

    /**
     * Transmit a single IR command to the TV.
     *
     * @param code The LG IR code to send
     * @return Result indicating success or failure reason
     */
    fun transmit(code: LgIrCode): IrResult {
        // 1. Check hardware availability
        if (irManager == null) {
            return IrResult.Error(IrError.NO_IR_SERVICE)
        }
        if (!hasIrBlaster) {
            return IrResult.Error(IrError.NO_IR_EMITTER)
        }
        if (!supportsLgFrequency) {
            return IrResult.Error(IrError.FREQUENCY_NOT_SUPPORTED)
        }

        // 2. Encode the NEC signal
        val pattern: IntArray
        try {
            pattern = NecEncoder.encode(code.hexCode)
        } catch (e: IllegalArgumentException) {
            return IrResult.Error(IrError.INVALID_CODE, e.message)
        }

        // 3. Transmit
        return try {
            irManager.transmit(NecEncoder.CARRIER_FREQUENCY_HZ, pattern)
            IrResult.Success(code)
        } catch (e: SecurityException) {
            IrResult.Error(IrError.PERMISSION_DENIED, e.message)
        } catch (e: Exception) {
            IrResult.Error(IrError.TRANSMIT_FAILED, e.message)
        }
    }

    /**
     * Transmit an IR command by its name (case-insensitive lookup in the database).
     */
    fun transmitByName(name: String): IrResult {
        val code = LgIrDatabase.findByName(name)
            ?: return IrResult.Error(IrError.CODE_NOT_FOUND, "Unknown code: $name")
        return transmit(code)
    }
}

/**
 * Result of an IR transmission attempt.
 */
sealed class IrResult {
    data class Success(val code: LgIrCode) : IrResult()
    data class Error(val reason: IrError, val message: String? = null) : IrResult()
}

/**
 * Possible IR transmission error categories.
 */
enum class IrError(val userMessage: String) {
    NO_IR_SERVICE("IR servisi kullanılamıyor"),
    NO_IR_EMITTER("Cihazınızda IR verici (blaster) bulunmuyor"),
    FREQUENCY_NOT_SUPPORTED("Cihazınız LG TV frekansını (38 kHz) desteklemiyor"),
    INVALID_CODE("Geçersiz IR kodu"),
    PERMISSION_DENIED("IR izni reddedildi"),
    TRANSMIT_FAILED("IR sinyali gönderilemedi"),
    CODE_NOT_FOUND("Komut bulunamadı")
}
