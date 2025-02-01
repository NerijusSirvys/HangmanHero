package com.ns.hangmanhero.screens.game

import androidx.lifecycle.ViewModel
import com.ns.hangmanhero.HangmanService
import com.ns.hangmanhero.StateFactory
import com.ns.hangmanhero.data.Difficulty
import com.ns.hangmanhero.data.Level
import com.ns.hangmanhero.data.Strength
import com.ns.hangmanhero.screens.game.data.KeyboardRow
import com.ns.hangmanhero.screens.game.states.GameScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameScreenViewmodel(
    service: HangmanService
) : ViewModel() {

    private val level: Level = service.getLevel(Difficulty.EASY)
    private val initialState = GameScreenState(
        keyCount = 0,
        clue = level.clue,
        answer = StateFactory.createAnswerState(level.answer),
        hints = mapOf(
            Strength.WEAK to StateFactory.createHintState(level.hints, Strength.WEAK),
            Strength.MEDIUM to StateFactory.createHintState(level.hints, Strength.MEDIUM),
            Strength.STRONG to StateFactory.createHintState(level.hints, Strength.STRONG),
        ),
        keyboard = mapOf(
            KeyboardRow.TOP to StateFactory.createKeyboardKeyState(KeyboardRow.TOP),
            KeyboardRow.MIDDLE to StateFactory.createKeyboardKeyState(KeyboardRow.MIDDLE),
            KeyboardRow.BOTTOM to StateFactory.createKeyboardKeyState(KeyboardRow.BOTTOM),
        )
    )

    private val _state: MutableStateFlow<GameScreenState> = MutableStateFlow(initialState)
    val state: StateFlow<GameScreenState> = _state.asStateFlow()

    fun onAction(action: GameScreenActions) {

        when (action) {
            is GameScreenActions.MakeAGuess -> guessLetter(action.character, action.row)
            is GameScreenActions.ShowHint -> showHint(action.strength)
        }
    }

    private fun showHint(strength: Strength) {
        _state.update { currentState ->
            val updatedHints = currentState.hints.toMutableMap().apply {
                currentState.hints[strength]?.let { currentHint ->
                    put(strength, currentHint.copy(isEnabled = false))
                }
            }
            currentState.copy(hints = updatedHints)
        }
    }

    private fun guessLetter(character: Char, row: KeyboardRow) {
        _state.update { currentState ->
            val updatedKeyboard = currentState.keyboard[row]?.map {
                if (it.character == character)
                    it.copy(isEnabled = false)
                else it
            } ?: emptyList()

            val updatedAnswer = currentState.answer.map {
                if (it.letter == character) it.copy(show = true) else it
            }

            currentState.copy(
                answer = updatedAnswer,
                keyboard = currentState.keyboard.toMutableMap().apply {
                    put(row, updatedKeyboard)
                })
        }
    }
}