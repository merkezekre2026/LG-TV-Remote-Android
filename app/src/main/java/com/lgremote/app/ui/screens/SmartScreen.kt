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
 * Smart TV & Input screen: Streaming apps, HDMI inputs, settings, extras.
 */
@Composable
fun SmartScreen(
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

        Spacer(modifier = Modifier.height(16.dp))

        // ─── Streaming Apps ───
        SectionTitle("UYGULAMALAR")

        // Row 1: Netflix - Amazon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "Netflix",
                onClick = { viewModel.transmitByName("NETFLIX") },
                modifier = Modifier.weight(1f),
                minHeight = 56,
                fontSize = 15,
                color = Color(0xFFE50914),
                contentColor = Color.White,
                highlighted = uiState.lastTransmitted?.name == "NETFLIX"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "Prime Video",
                onClick = { viewModel.transmitByName("AMAZON") },
                modifier = Modifier.weight(1f),
                minHeight = 56,
                fontSize = 13,
                color = Color(0xFF00A8E1),
                contentColor = Color.White,
                highlighted = uiState.lastTransmitted?.name == "AMAZON"
            )
        }

        // Row 2: Disney+ - Apps
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "Disney+",
                onClick = { viewModel.transmitByName("DISNEYPLUS") },
                modifier = Modifier.weight(1f),
                minHeight = 56,
                fontSize = 15,
                color = Color(0xFF113CCF),
                contentColor = Color.White,
                highlighted = uiState.lastTransmitted?.name == "DISNEYPLUS"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "Apps",
                onClick = { viewModel.transmitByName("APPS") },
                modifier = Modifier.weight(1f),
                minHeight = 56,
                fontSize = 15,
                highlighted = uiState.lastTransmitted?.name == "APPS"
            )
        }

        // Row 3: Others
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "LG Channels",
                onClick = { viewModel.transmitByName("LGCHANNELS") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 12,
                highlighted = uiState.lastTransmitted?.name == "LGCHANNELS"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "Rakuten TV",
                onClick = { viewModel.transmitByName("RAKUTENTV") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 12,
                highlighted = uiState.lastTransmitted?.name == "RAKUTENTV"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "Spor",
                onClick = { viewModel.transmitByName("SPORTS") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 12,
                highlighted = uiState.lastTransmitted?.name == "SPORTS"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ─── HDMI Inputs ───
        SectionTitle("GİRİŞ KAYNAKLARI")

        // Row 1: LiveTV - Input
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "📺 Canlı TV",
                onClick = { viewModel.transmitByName("LIVETV") },
                modifier = Modifier.weight(1f),
                minHeight = 52,
                fontSize = 13,
                highlighted = uiState.lastTransmitted?.name == "LIVETV"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "📶 Input",
                onClick = { viewModel.transmitByName("INPUT") },
                modifier = Modifier.weight(1f),
                minHeight = 52,
                fontSize = 13,
                highlighted = uiState.lastTransmitted?.name == "INPUT"
            )
        }

        // Row 2: HDMI 1 - HDMI 2
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "HDMI 1",
                onClick = { viewModel.transmitByName("HDMI1") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 13,
                color = Color(0xFF4A148C),
                contentColor = Color.White,
                highlighted = uiState.lastTransmitted?.name == "HDMI1"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "HDMI 2",
                onClick = { viewModel.transmitByName("HDMI2") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 13,
                color = Color(0xFF4A148C),
                contentColor = Color.White,
                highlighted = uiState.lastTransmitted?.name == "HDMI2"
            )
        }

        // Row 3: HDMI 3 - HDMI 4
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "HDMI 3",
                onClick = { viewModel.transmitByName("HDMI3") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 13,
                color = Color(0xFF4A148C),
                contentColor = Color.White,
                highlighted = uiState.lastTransmitted?.name == "HDMI3"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "HDMI 4",
                onClick = { viewModel.transmitByName("HDMI4") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 13,
                color = Color(0xFF4A148C),
                contentColor = Color.White,
                highlighted = uiState.lastTransmitted?.name == "HDMI4"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ─── Settings & Extras ───
        SectionTitle("AYARLAR & DİĞER")

        // Row 1: Settings - QMenu
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "⚙️ Ayarlar",
                onClick = { viewModel.transmitByName("SETTINGS") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 13,
                highlighted = uiState.lastTransmitted?.name == "SETTINGS"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "Hızlı Menü",
                onClick = { viewModel.transmitByName("QMENU") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 13,
                highlighted = uiState.lastTransmitted?.name == "QMENU"
            )
        }

        // Row 2: Sleep - Aspect
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "😴 Uyku",
                onClick = { viewModel.transmitByName("SLEEP") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 13,
                highlighted = uiState.lastTransmitted?.name == "SLEEP"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "Ekran Oranı",
                onClick = { viewModel.transmitByName("ASPECT") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 13,
                highlighted = uiState.lastTransmitted?.name == "ASPECT"
            )
        }

        // Row 3: Picture - Sound - Game
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "Resim",
                onClick = { viewModel.transmitByName("PICTURE_MODE") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 12,
                highlighted = uiState.lastTransmitted?.name == "PICTURE_MODE"
            )
            Spacer(modifier = Modifier.width(6.dp))
            RemoteButton(
                text = "Ses",
                onClick = { viewModel.transmitByName("SOUND_MODE") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 12,
                highlighted = uiState.lastTransmitted?.name == "SOUND_MODE"
            )
            Spacer(modifier = Modifier.width(6.dp))
            RemoteButton(
                text = "Oyun",
                onClick = { viewModel.transmitByName("GAME_MODE") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 12,
                highlighted = uiState.lastTransmitted?.name == "GAME_MODE"
            )
        }

        // Row 4: LiveZoom - Simplink - AudioDesc
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "Zoom",
                onClick = { viewModel.transmitByName("LIVE_ZOOM") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 12,
                highlighted = uiState.lastTransmitted?.name == "LIVE_ZOOM"
            )
            Spacer(modifier = Modifier.width(6.dp))
            RemoteButton(
                text = "Simplink",
                onClick = { viewModel.transmitByName("SIMPLINK") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 12,
                highlighted = uiState.lastTransmitted?.name == "SIMPLINK"
            )
            Spacer(modifier = Modifier.width(6.dp))
            RemoteButton(
                text = "Sesli Bet.",
                onClick = { viewModel.transmitByName("AUDIO_DESC") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 11,
                highlighted = uiState.lastTransmitted?.name == "AUDIO_DESC"
            )
        }

        // Row 5: Help - Pairing
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RemoteButton(
                text = "❓ Yardım",
                onClick = { viewModel.transmitByName("HELP") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 13,
                highlighted = uiState.lastTransmitted?.name == "HELP"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RemoteButton(
                text = "🔗 Eşleştir",
                onClick = { viewModel.transmitByName("PAIRING") },
                modifier = Modifier.weight(1f),
                minHeight = 48,
                fontSize = 13,
                highlighted = uiState.lastTransmitted?.name == "PAIRING"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
