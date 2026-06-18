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
import androidx.compose.ui.unit.dp
import com.lgremote.app.viewmodel.RemoteViewModel

/**
 * Media control screen: Play, Pause, Stop, Rewind, Fast Forward,
 * Previous, Next, Record, Record List.
 */
@Composable
fun MediaScreen(
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

        SectionTitle("MEDYA KONTROLLERİ")

        // Row 1: Previous - Play - Next
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RemoteButton(
                text = "⏮",
                onClick = { viewModel.transmitByName("PREVIOUS") },
                modifier = Modifier.weight(1f),
                minHeight = 60,
                fontSize = 24,
                highlighted = uiState.lastTransmitted?.name == "PREVIOUS"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "▶",
                onClick = { viewModel.transmitByName("PLAY") },
                modifier = Modifier.weight(1.5f),
                minHeight = 64,
                fontSize = 26,
                highlighted = uiState.lastTransmitted?.name == "PLAY"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "⏭",
                onClick = { viewModel.transmitByName("NEXT") },
                modifier = Modifier.weight(1f),
                minHeight = 60,
                fontSize = 24,
                highlighted = uiState.lastTransmitted?.name == "NEXT"
            )
        }

        // Row 2: Rewind - Pause - Fast Forward
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "⏪",
                onClick = { viewModel.transmitByName("REWIND") },
                modifier = Modifier.weight(1f),
                minHeight = 60,
                fontSize = 24,
                highlighted = uiState.lastTransmitted?.name == "REWIND"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "⏸",
                onClick = { viewModel.transmitByName("PAUSE") },
                modifier = Modifier.weight(1.5f),
                minHeight = 64,
                fontSize = 26,
                highlighted = uiState.lastTransmitted?.name == "PAUSE"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "⏩",
                onClick = { viewModel.transmitByName("FAST_FORWARD") },
                modifier = Modifier.weight(1f),
                minHeight = 60,
                fontSize = 24,
                highlighted = uiState.lastTransmitted?.name == "FAST_FORWARD"
            )
        }

        // Row 3: Stop - Record
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "⏹ Stop",
                onClick = { viewModel.transmitByName("STOP") },
                modifier = Modifier.weight(1f),
                minHeight = 56,
                fontSize = 16,
                highlighted = uiState.lastTransmitted?.name == "STOP"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "⏺ Rec",
                onClick = { viewModel.transmitByName("RECORD") },
                modifier = Modifier.weight(1f),
                minHeight = 56,
                fontSize = 16,
                highlighted = uiState.lastTransmitted?.name == "RECORD"
            )
        }

        // Record list
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "📋 Kayıt Listesi",
                onClick = { viewModel.transmitByName("RECLIST") },
                modifier = Modifier.weight(1f),
                minHeight = 52,
                fontSize = 14,
                highlighted = uiState.lastTransmitted?.name == "RECLIST"
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        SectionTitle("RENKLİ BUTONLAR")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "🔴",
                onClick = { viewModel.transmitByName("RED") },
                modifier = Modifier.weight(1f),
                minHeight = 56,
                fontSize = 20,
                color = androidx.compose.ui.graphics.Color(0xFF8B0000),
                contentColor = androidx.compose.ui.graphics.Color.White,
                highlighted = uiState.lastTransmitted?.name == "RED"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "🟢",
                onClick = { viewModel.transmitByName("GREEN") },
                modifier = Modifier.weight(1f),
                minHeight = 56,
                fontSize = 20,
                color = androidx.compose.ui.graphics.Color(0xFF006400),
                contentColor = androidx.compose.ui.graphics.Color.White,
                highlighted = uiState.lastTransmitted?.name == "GREEN"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "🟡",
                onClick = { viewModel.transmitByName("YELLOW") },
                modifier = Modifier.weight(1f),
                minHeight = 56,
                fontSize = 20,
                color = androidx.compose.ui.graphics.Color(0xFF8B7500),
                contentColor = androidx.compose.ui.graphics.Color.White,
                highlighted = uiState.lastTransmitted?.name == "YELLOW"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "🔵",
                onClick = { viewModel.transmitByName("BLUE") },
                modifier = Modifier.weight(1f),
                minHeight = 56,
                fontSize = 20,
                color = androidx.compose.ui.graphics.Color(0xFF00008B),
                contentColor = androidx.compose.ui.graphics.Color.White,
                highlighted = uiState.lastTransmitted?.name == "BLUE"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
