package com.ns.hangmanhero

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ns.hangmanhero.actions.GameActions
import com.ns.hangmanhero.data.Difficulty
import com.ns.hangmanhero.data.Level
import com.ns.hangmanhero.data.Stage
import com.ns.hangmanhero.data.Strength
import com.ns.hangmanhero.data.TransferableState
import com.ns.hangmanhero.stages.game_play.data.KeyboardRow
import com.ns.hangmanhero.state.CharacterState
import com.ns.hangmanhero.state.StateFactory
import com.ns.hangmanhero.utils.SnackbarController
import com.ns.hangmanhero.utils.SnackbarEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewmodel(
    private val service: GameService,
    context: Context
) : ViewModel() {

    private val context: Context by lazy { context }
    private var level: Level = service.getLevel(Difficulty.EASY)

    private val _state = MutableStateFlow(StateFactory.createGameState(level, TransferableState()))
    val state = _state.asStateFlow()

    fun onGameAction(action: GameActions) {
        when (action) {
            is GameActions.MakeAGuess -> guessLetter(action.character, action.row)
            is GameActions.ShowHint -> showHint(action.strength)
            GameActions.Exit -> exitApplication()
            GameActions.NextLevel -> goToNextLevel()
            GameActions.Restart -> restart()
            GameActions.SaveState -> saveState()
        }
    }

    private fun saveState() {
        _state.update { state ->
            val levels = state.completeLevels.toMutableList()
            state.currentLevel?.let { levels.add(it) }
            state.copy(completeLevels = levels, currentLevel = null)
        }

        service.saveState(_state.value)

        println("---> Saving State <---")
    }

    private fun goToNextLevel() {
        val newLevel = service.getNewLevel(level.difficulty, false)
        if (newLevel != null) {
            level = newLevel
        }

        val transferObject = TransferableState(
            keys = _state.value.keyCount,
            completeLevels = _state.value.completeLevels
        )

        _state.update { StateFactory.createGameState(level, transferObject) }
    }

    private fun restart() {
        val newLevel = service.getNewLevel(level.difficulty, true)
        if (newLevel != null) {
            level = newLevel
        }

        val keyCount = if (_state.value.keyCount <= 3) 0 else _state.value.keyCount - 3
        _state.update { StateFactory.createGameState(level, TransferableState(keys = keyCount)) }
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
                if (it.letter == character) {
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
}