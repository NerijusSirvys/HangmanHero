package com.ns.hangmanhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ns.hangmanhero.components.InfoTab
import com.ns.hangmanhero.stages.game_complete.GameCompleteStage
import com.ns.hangmanhero.stages.game_over.GameOverScreen
import com.ns.hangmanhero.stages.game_play.GamePlayStage
import com.ns.hangmanhero.stages.next_level.NextLevelScreen
import com.ns.hangmanhero.ui.theme.HangmanHeroTheme
import com.ns.hangmanhero.utils.FrameHelpers
import com.ns.hangmanhero.utils.ObserveAsEvents
import com.ns.hangmanhero.utils.SnackbarController
import com.ns.hangmanhero.viewModel.GameViewmodel
import com.ns.hangmanhero.viewModel.models.Stage
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      val splash = installSplashScreen()
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         val snackbarHostState = remember { SnackbarHostState() }

         val scope = rememberCoroutineScope()
         ObserveAsEvents(
            flow = SnackbarController.events,
            key1 = snackbarHostState
         ) { event ->
            scope.launch {
               snackbarHostState.currentSnackbarData?.dismiss()
               snackbarHostState.showSnackbar(
                  message = event.message,
                  duration = SnackbarDuration.Short
               )
            }
         }

         val vm = koinViewModel<GameViewmodel>()
         val state by vm.state.collectAsStateWithLifecycle()

         splash.setKeepOnScreenCondition {
            state.isLoading
         }

         HangmanHeroTheme {
            Scaffold(
               snackbarHost = { SnackbarHost(snackbarHostState) },
            ) { innerPadding ->

               Column(
                  modifier = Modifier
                     .padding(innerPadding)
                     .padding(vertical = 10.dp, horizontal = 15.dp)
                     .fillMaxSize()
               ) {
                  if (state.isLoading) {
                     Text(text = "Loading...")
                     return@Column
                  }

                  // IMAGE AND INFO TABS
                  Box(
                     modifier = Modifier
                        .fillMaxHeight(.3f)
                        .fillMaxWidth()

                  ) {
                     // IMAGE
                     Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                     ) {
                        Image(
                           painter = painterResource(FrameHelpers.updateFrame(state.remainingGuesses)),
                           contentDescription = null,
                           alpha = 0.6f,
                        )
                     }

                     Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        InfoTab(
                           iconId = R.drawable.icon_key,
                           value = state.keyCount
                        )

                        InfoTab(
                           iconId = R.drawable.icon_check,
                           value = state.completeLevels
                        )
                     }
                  }
                  Spacer(Modifier.height(25.dp))
                  Column(
                     modifier = Modifier
                        .fillMaxSize()
                  ) {
                     AnimatedContent(
                        targetState = state.stage,
                     ) {
                        when (it) {
                           Stage.Game -> GamePlayStage(state = state, onAction = vm::onGameAction)
                           Stage.GameOver -> GameOverScreen(onAction = vm::onGameAction)
                           Stage.NextLevel -> NextLevelScreen(onAction = vm::onGameAction)
                           Stage.GameCompleted -> GameCompleteStage(onAction = vm::onGameAction)
                        }
                     }
                  }
               }
            }
         }
      }
   }
}