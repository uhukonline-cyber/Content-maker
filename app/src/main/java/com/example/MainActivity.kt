package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ui.screens.EditorShareScreen
import com.example.ui.screens.GeneratorScreen
import com.example.ui.screens.HookBankScreen
import com.example.ui.screens.SavedDraftsScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.ViralGeneratorViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: ViralGeneratorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                ViralCraftApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun ViralCraftApp(
    viewModel: ViralGeneratorViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val savedDrafts by viewModel.savedDrafts.collectAsState()

    // Handle back button on sub-screens
    if (uiState.currentScreen != AppScreen.GENERATOR) {
        BackHandler {
            viewModel.navigateTo(AppScreen.GENERATOR)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ) {
                AppScreen.entries.forEach { screen ->
                    val isSelected = uiState.currentScreen == screen
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { viewModel.navigateTo(screen) },
                        modifier = Modifier.testTag("nav_item_${screen.name.lowercase()}"),
                        icon = {
                            if (screen == AppScreen.SAVED && savedDrafts.isNotEmpty()) {
                                BadgedBox(badge = {
                                    Badge { Text("${savedDrafts.size}") }
                                }) {
                                    Text(text = screen.iconEmoji, fontSize = 20.sp)
                                }
                            } else {
                                Text(text = screen.iconEmoji, fontSize = 20.sp)
                            }
                        },
                        label = {
                            Text(
                                text = screen.label,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState.currentScreen) {
                AppScreen.GENERATOR -> {
                    GeneratorScreen(
                        viewModel = viewModel,
                        uiState = uiState
                    )
                }
                AppScreen.EDITOR -> {
                    EditorShareScreen(
                        viewModel = viewModel,
                        uiState = uiState
                    )
                }
                AppScreen.SAVED -> {
                    SavedDraftsScreen(
                        drafts = savedDrafts,
                        viewModel = viewModel
                    )
                }
                AppScreen.HOOK_BANK -> {
                    HookBankScreen(
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
