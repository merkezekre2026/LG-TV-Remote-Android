package com.lgremote.app

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lgremote.app.ui.navigation.RemoteTab
import com.lgremote.app.ui.screens.MainRemoteScreen
import com.lgremote.app.ui.screens.MediaScreen
import com.lgremote.app.ui.screens.NumpadScreen
import com.lgremote.app.ui.screens.SmartScreen
import com.lgremote.app.ui.theme.LGRemoteTheme
import com.lgremote.app.viewmodel.RemoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Keep screen on while using the remote
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        setContent {
            LGRemoteTheme {
                LGRemoteApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LGRemoteApp() {
    val viewModel: RemoteViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    var selectedTab by remember { mutableStateOf(RemoteTab.MAIN) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "LG Remote",
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                RemoteTab.entries.forEach { tab ->
                    NavigationBarItem(
                        selected = selectedTab == tab,
                        onClick = {
                            selectedTab = tab
                            viewModel.clearFeedback()
                        },
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.label
                            )
                        },
                        label = { Text(tab.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        val contentModifier = Modifier.padding(innerPadding)
        when (selectedTab) {
            RemoteTab.MAIN -> MainRemoteScreen(
                viewModel = viewModel,
                modifier = contentModifier
            )
            RemoteTab.NUMPAD -> NumpadScreen(
                viewModel = viewModel,
                modifier = contentModifier
            )
            RemoteTab.MEDIA -> MediaScreen(
                viewModel = viewModel,
                modifier = contentModifier
            )
            RemoteTab.SMART -> SmartScreen(
                viewModel = viewModel,
                modifier = contentModifier
            )
        }
    }
}
