package com.lgremote.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lgremote.app.viewmodel.RemoteViewModel

/**
 * Numeric keypad screen: 0-9, dash, channel back, enter.
 */
@Composable
fun NumpadScreen(
    viewModel: RemoteViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IrStatusBar(uiState)

        Spacer(modifier = Modifier.height(20.dp))

        SectionTitle("SAYI TUŞLARI")

        // Row 1: 1 2 3
        NumpadRow(
            viewModel = viewModel,
            uiState = uiState,
            buttons = listOf("1", "2", "3"),
            names = listOf("NUM_1", "NUM_2", "NUM_3")
        )

        // Row 2: 4 5 6
        NumpadRow(
            viewModel = viewModel,
            uiState = uiState,
            buttons = listOf("4", "5", "6"),
            names = listOf("NUM_4", "NUM_5", "NUM_6")
        )

        // Row 3: 7 8 9
        NumpadRow(
            viewModel = viewModel,
            uiState = uiState,
            buttons = listOf("7", "8", "9"),
            names = listOf("NUM_7", "NUM_8", "NUM_9")
        )

        // Row 4: - 0 Enter
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "-",
                onClick = { viewModel.transmitByName("DASH") },
                modifier = Modifier.weight(1f),
                minHeight = 60,
                fontSize = 22,
                highlighted = uiState.lastTransmitted?.name == "DASH"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "0",
                onClick = { viewModel.transmitByName("NUM_0") },
                modifier = Modifier.weight(1f),
                minHeight = 60,
                fontSize = 22,
                highlighted = uiState.lastTransmitted?.name == "NUM_0"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "↵",
                onClick = { viewModel.transmitByName("ENTER") },
                modifier = Modifier.weight(1f),
                minHeight = 60,
                fontSize = 22,
                highlighted = uiState.lastTransmitted?.name == "ENTER"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        SectionTitle("KANAL KONTROLLERİ")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "⏮ Önceki",
                onClick = { viewModel.transmitByName("FLASHBACK") },
                modifier = Modifier.weight(1f),
                minHeight = 52,
                highlighted = uiState.lastTransmitted?.name == "FLASHBACK"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "📋 Liste",
                onClick = { viewModel.transmitByName("PRLIST") },
                modifier = Modifier.weight(1f),
                minHeight = 52,
                highlighted = uiState.lastTransmitted?.name == "PRLIST"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun NumpadRow(
    viewModel: RemoteViewModel,
    uiState: com.lgremote.app.viewmodel.RemoteUiState,
    buttons: List<String>,
    names: List<String>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        buttons.forEachIndexed { index, text ->
            RemoteButton(
                text = text,
                onClick = { viewModel.transmitByName(names[index]) },
                modifier = Modifier.weight(1f),
                minHeight = 60,
                fontSize = 22,
                highlighted = uiState.lastTransmitted?.name == names[index]
            )
            if (index < buttons.lastIndex) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}
