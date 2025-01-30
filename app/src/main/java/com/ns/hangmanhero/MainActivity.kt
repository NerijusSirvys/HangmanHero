package com.ns.hangmanhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ns.hangmanhero.navigation.Destinations
import com.ns.hangmanhero.screens.game.GameScreen
import com.ns.hangmanhero.screens.game_over.GameOverScreen
import com.ns.hangmanhero.screens.next_level.NextLevelScreen
import com.ns.hangmanhero.ui.theme.HangmanHeroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HangmanHeroTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Destinations.GameScreen){
                        composable<Destinations.GameScreen> { GameScreen() }
                        composable<Destinations.GameOverScreen> { GameOverScreen() }
                        composable<Destinations.NextLevelScreen> { NextLevelScreen() }
                    }
                }
            }
        }
    }
}