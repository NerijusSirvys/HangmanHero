package com.ns.hangmanhero.viewModel

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ns.hangmanhero.GameService
import com.ns.hangmanhero.R
import com.ns.hangmanhero.actions.GameActions
import com.ns.hangmanhero.data.models.Difficulty
import com.ns.hangmanhero.data.models.Level
import com.ns.hangmanhero.data.models.Strength
import com.ns.hangmanhero.stages.game_play.data.KeyboardRow
import com.ns.hangmanhero.state.CharacterState
import com.ns.hangmanhero.state.GameState
import com.ns.hangmanhero.state.StateFactory
import com.ns.hangmanhero.state.TransferableState
import com.ns.hangmanhero.utils.SnackbarController
import com.ns.hangmanhero.utils.SnackbarEvent
import com.ns.hangmanhero.viewModel.models.NewLoadMap
import com.ns.hangmanhero.viewModel.models.Stage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class GameViewmodel(
   private val service: GameService,
   context: Context
) : ViewModel() {
   private val playerId = "8784f420-f95f-467c-aac4-1147b2e26e2d"

   private val context: Context by lazy { context }
   private lateinit var difficulty: Difficulty
   private lateinit var levelId: String

   private val _state: MutableStateFlow<GameState> = MutableStateFlow(GameState())
   val state = _state.asStateFlow()

   init {
      viewModelScope.launch {
         populateData()
         service.loadPlayerData(playerId)
            .zip(service.getRandomLevel(Difficulty.EASY)) { player, level ->
               NewLoadMap(player, level)
            }.collectLatest { loadMap ->
               val transferObject = TransferableState(
                  keys = loadMap.playerData.keys,
                  completeLevels = loadMap.playerData.completeLevels
               )

               if (loadMap.levelData != null) {
                  _state.value = StateFactory.createGameState(loadMap.levelData, transferObject)

                  difficulty = loadMap.levelData.difficulty
                  levelId = loadMap.levelData.id
               } else {
                  _state.update { it.copy(stage = Stage.GameCompleted, isLoading = false) }
                  _state.value = StateFactory.createGameState(null, transferObject)
               }
            }
      }
   }

   fun onGameAction(action: GameActions) {
      when (action) {
         is GameActions.MakeAGuess -> guessLetter(action.character, action.row)
         is GameActions.ShowHint -> showHint(action.strength)
         GameActions.Exit -> exitApplication()
         GameActions.NextLevel -> goToNextLevel()
         GameActions.Restart -> restart()
         GameActions.StartAgain -> resetGame()
      }
   }

   private fun resetGame() {
      viewModelScope.launch {
         service.resetData(playerId)
      }
   }

   private fun goToNextLevel() {
      _state.update {
         it.copy(
            isLoading = true,
            completeLevels = _state.value.completeLevels + 1
         )
      }
      viewModelScope.launch {
         service.completeLevel(levelId)
         service.saveState(_state.value, playerId)
      }
   }

   private fun restart() {
      val keyCount = if (_state.value.keyCount <= 3) 0 else _state.value.keyCount - 3
      _state.update { it.copy(isLoading = true) }

      viewModelScope.launch {
         service.getRandomLevel(difficulty).collectLatest { level ->
            if (level != null) {
               _state.update {
                  difficulty = level.difficulty
                  levelId = level.id
                  StateFactory.createGameState(level, TransferableState(keys = keyCount))
               }
            }
         }
      }
   }

   private fun exitApplication() {
      (context as Activity).finish()
   }

   private fun showHint(strength: Strength) {
      if (_state.value.keyCount < strength.value) {
         viewModelScope.launch {
            SnackbarController.sendEvents(
               SnackbarEvent(
                  message = "Nope, need more keys"
               )
            )
         }
      } else {
         _state.update { currentState ->
            val updatedHints = currentState.hints.toMutableMap().apply {
               currentState.hints[strength]?.let { currentHint ->
                  put(strength, currentHint.copy(isEnabled = false))
               }
            }
            currentState.copy(
               keyCount = currentState.keyCount - strength.value,
               hints = updatedHints
            )
         }
      }
   }

   private fun guessLetter(character: Char, row: KeyboardRow) {
      _state.update { currentState ->
         val updatedKeyboard = currentState.keyboard[row]?.map {
            if (it.character == character)
               it.copy(isEnabled = false)
            else it
         } ?: emptyList()

         var correct = false
         var fountKeys = 0

         val updatedAnswer = currentState.answer.map {
            if (it.letter.equals(character, true)) {
               correct = true

               if (it.keyHolder)
                  fountKeys++

               it.copy(show = true)
            } else it
         }

         val updatedGuesses = if (correct) currentState.remainingGuesses
         else currentState.remainingGuesses - 1

         currentState.copy(
            keyCount = currentState.keyCount + fountKeys,
            stage = updateStage(updatedGuesses, updatedAnswer) ?: currentState.stage,
            remainingGuesses = updatedGuesses,
            answer = updatedAnswer,
            keyboard = currentState.keyboard.toMutableMap().apply {
               put(row, updatedKeyboard)
            })
      }
   }

   private fun updateStage(updatedGuesses: Int, answer: List<CharacterState>): Stage? {
      if (updatedGuesses == 0) {
         return Stage.GameOver
      } else if (answer.filter { it.show }.size == answer.size) {
         return Stage.NextLevel
      }

      return null
   }

   private suspend fun populateData() {
      val sharedPref = context.getSharedPreferences("hangman", Context.MODE_PRIVATE)

      val populated = sharedPref.getBoolean("db_populated", false)
      if (!populated) {
         val easy = context.resources.openRawResource(R.raw.easy)
         val easyJson = easy.readBytes().decodeToString()
         val easyLevels = Json.decodeFromString<List<Level>>(easyJson)

         val medium = context.resources.openRawResource(R.raw.medium)
         val mediumJson = medium.readBytes().decodeToString()
         val mediumLevels = Json.decodeFromString<List<Level>>(mediumJson)

         service.populateData(easyLevels)
         service.populateData(mediumLevels)

         val editor = sharedPref.edit()
         editor.putBoolean("db_populated", true)
         editor.apply()
      }
   }
}