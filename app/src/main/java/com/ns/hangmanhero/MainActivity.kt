package com.ns.hangmanhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ns.hangmanhero.navigation.Destinations
import com.ns.hangmanhero.screens.game.GameScreen
import com.ns.hangmanhero.screens.game.GameScreenViewmodel
import com.ns.hangmanhero.screens.game_over.GameOverScreen
import com.ns.hangmanhero.screens.next_level.NextLevelScreen
import com.ns.hangmanhero.ui.theme.HangmanHeroTheme
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            modules(appModule)
        }

        enableEdgeToEdge()
        setContent {
            HangmanHeroTheme {
                KoinContext {
                    Surface {
                        val navController = rememberNavController()
                        NavHost(navController = navController, startDestination = Destinations.GameScreen) {
                            composable<Destinations.GameScreen> {
                                val vm = koinViewModel<GameScreenViewmodel>()
                                val state by vm.state.collectAsStateWithLifecycle()

                                GameScreen(
                                    onActions = vm::onAction,
                                    state = state
                                )
                            }
                            composable<Destinations.GameOverScreen> { GameOverScreen() }
                            composable<Destinations.NextLevelScreen> { NextLevelScreen() }
                        }
                    }
                }
            }
        }
    }
}