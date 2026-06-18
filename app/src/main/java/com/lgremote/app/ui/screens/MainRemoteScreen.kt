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
import com.lgremote.app.data.LgIrDatabase
import com.lgremote.app.viewmodel.RemoteViewModel

/**
 * Main remote screen: Power, D-pad + OK, Volume, Channel, Navigation buttons.
 */
@Composable
fun MainRemoteScreen(
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

        Spacer(modifier = Modifier.height(12.dp))

        // ─── Power ───
        SectionTitle("GÜÇ")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "⏻ Power",
                onClick = { viewModel.transmitByName("POWER") },
                color = Color(0xFFC62828),
                contentColor = Color.White,
                minHeight = 60,
                fontSize = 16,
                highlighted = uiState.lastTransmitted?.name == "POWER"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ─── D-Pad + OK ───
        SectionTitle("NAVİGASYON")
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // UP
            CircleRemoteButton(
                text = "▲",
                onClick = { viewModel.transmitByName("UP") },
                highlighted = uiState.lastTransmitted?.name == "UP"
            )
            // LEFT - OK - RIGHT
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CircleRemoteButton(
                    text = "◀",
                    onClick = { viewModel.transmitByName("LEFT") },
                    highlighted = uiState.lastTransmitted?.name == "LEFT"
                )
                CircleRemoteButton(
                    text = "OK",
                    onClick = { viewModel.transmitByName("OK") },
                    size = 64,
                    fontSize = 16,
                    highlighted = uiState.lastTransmitted?.name == "OK"
                )
                CircleRemoteButton(
                    text = "▶",
                    onClick = { viewModel.transmitByName("RIGHT") },
                    highlighted = uiState.lastTransmitted?.name == "RIGHT"
                )
            }
            // DOWN
            CircleRemoteButton(
                text = "▼",
                onClick = { viewModel.transmitByName("DOWN") },
                highlighted = uiState.lastTransmitted?.name == "DOWN"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ─── Volume ───
        SectionTitle("SES")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RemoteButton(
                text = "－",
                onClick = { viewModel.transmitByName("VOL_DOWN") },
                modifier = Modifier.weight(1f),
                minHeight = 56,
                fontSize = 22,
                highlighted = uiState.lastTransmitted?.name == "VOL_DOWN"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "🔇",
                onClick = { viewModel.transmitByName("MUTE") },
                modifier = Modifier.weight(1f),
                minHeight = 56,
                highlighted = uiState.lastTransmitted?.name == "MUTE"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "＋",
                onClick = { viewModel.transmitByName("VOL_UP") },
                modifier = Modifier.weight(1f),
                minHeight = 56,
                fontSize = 22,
                highlighted = uiState.lastTransmitted?.name == "VOL_UP"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ─── Channel ───
        SectionTitle("KANAL")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "▼ CH",
                onClick = { viewModel.transmitByName("CH_DOWN") },
                modifier = Modifier.weight(1f),
                minHeight = 52,
                highlighted = uiState.lastTransmitted?.name == "CH_DOWN"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "CH ▲",
                onClick = { viewModel.transmitByName("CH_UP") },
                modifier = Modifier.weight(1f),
                minHeight = 52,
                highlighted = uiState.lastTransmitted?.name == "CH_UP"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ─── Navigation buttons ───
        SectionTitle("KONTROLLER")
        // Row 1: Back - Home - Exit
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "↩ Geri",
                onClick = { viewModel.transmitByName("BACK") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                highlighted = uiState.lastTransmitted?.name == "BACK"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "⌂ Home",
                onClick = { viewModel.transmitByName("HOME") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                highlighted = uiState.lastTransmitted?.name == "HOME"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "✕ Exit",
                onClick = { viewModel.transmitByName("EXIT") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                highlighted = uiState.lastTransmitted?.name == "EXIT"
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Row 2: Menu - Search - Info
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "☰ Menü",
                onClick = { viewModel.transmitByName("MENU") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                highlighted = uiState.lastTransmitted?.name == "MENU"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "🔍 Ara",
                onClick = { viewModel.transmitByName("SEARCH") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                highlighted = uiState.lastTransmitted?.name == "SEARCH"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "🛈 Info",
                onClick = { viewModel.transmitByName("INFO") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                highlighted = uiState.lastTransmitted?.name == "INFO"
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Row 3: Guide - Favorite - Recent
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "📺 Rehber",
                onClick = { viewModel.transmitByName("GUIDE") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                highlighted = uiState.lastTransmitted?.name == "GUIDE"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "★ Favori",
                onClick = { viewModel.transmitByName("FAVORITE") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                highlighted = uiState.lastTransmitted?.name == "FAVORITE"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "🕐 Son",
                onClick = { viewModel.transmitByName("RECENT") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                highlighted = uiState.lastTransmitted?.name == "RECENT"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
