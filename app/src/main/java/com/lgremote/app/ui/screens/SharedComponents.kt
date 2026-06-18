package com.lgremote.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lgremote.app.data.LgIrCode
import com.lgremote.app.viewmodel.RemoteUiState

/**
 * A rounded square remote button styled for the dark theme.
 */
@Composable
fun RemoteButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    fontSize: Int = 14,
    minHeight: Int = 52,
    highlighted: Boolean = false
) {
    val bgColor = if (highlighted) MaterialTheme.colorScheme.primary.copy(alpha = 0.6f) else color

    Button(
        onClick = onClick,
        modifier = modifier
            .defaultMinSize(minHeight = minHeight.dp)
            .clip(RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = bgColor,
            contentColor = contentColor
        ),
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            maxLines = 2
        )
    }
}

/**
 * Circular button used for D-pad and OK.
 */
@Composable
fun CircleRemoteButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surfaceVariant,
    size: Int = 56,
    fontSize: Int = 18,
    highlighted: Boolean = false
) {
    val bgColor = if (highlighted) MaterialTheme.colorScheme.primary.copy(alpha = 0.6f) else color

    Button(
        onClick = onClick,
        modifier = modifier.size(size.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = bgColor,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * Top status bar showing IR hardware status and last action feedback.
 */
@Composable
fun IrStatusBar(uiState: RemoteUiState) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = if (uiState.isIrSupported)
            Color(0xFF1B5E20).copy(alpha = 0.3f)
        else
            Color(0xFFB71C1C).copy(alpha = 0.3f)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = uiState.irStatusMessage,
                color = if (uiState.isIrSupported) Color(0xFF81C784) else Color(0xFFEF9A9A),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            uiState.lastTransmitted?.let { code ->
                Text(
                    text = "✓ Gönderildi: ${code.displayName}",
                    color = Color(0xFF81C784),
                    fontSize = 11.sp
                )
            }
            uiState.lastError?.let { error ->
                Text(
                    text = "✗ $error",
                    color = Color(0xFFEF9A9A),
                    fontSize = 11.sp
                )
            }
        }
    }
}

/**
 * Section title for button groups.
 */
@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.primary,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 1.sp,
        modifier = Modifier.padding(start = 12.dp, bottom = 4.dp)
    )
}
