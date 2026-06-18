package com.lgremote.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Dialpad
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Tv
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Navigation destinations for the bottom tab bar.
 */
enum class RemoteTab(
    val label: String,
    val icon: ImageVector
) {
    MAIN("Kumanda", Icons.Filled.Tv),
    NUMPAD("NumPad", Icons.Filled.Dialpad),
    MEDIA("Medya", Icons.Filled.PlayArrow),
    SMART("Smart", Icons.Filled.Apps)
}
