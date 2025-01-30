package com.ns.hangmanhero.screens.game

import androidx.lifecycle.ViewModel
import com.ns.hangmanhero.HangmanService
import com.ns.hangmanhero.data.Difficulty
import com.ns.hangmanhero.data.Level
import com.ns.hangmanhero.data.Strength
import com.ns.hangmanhero.mappers.toHintElementState
import com.ns.hangmanhero.screens.game.data.KeyboardRow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class GameScreenViewmodel(
    service: HangmanService
) : ViewModel() {

    private val level: Level = service.getLevel(Difficulty.EASY)

    val topRowState = MutableStateFlow(service.getKeyboardKeys(KeyboardRow.TOP))
    val midRowState = MutableStateFlow(service.getKeyboardKeys(KeyboardRow.MIDDLE))
    val bottomRowState = MutableStateFlow(service.getKeyboardKeys(KeyboardRow.BOTTOM))

    val weakHintState = MutableStateFlow(level.hints.find { it.strength == Strength.WEAK }!!.toHintElementState())
    val mediumHintState = MutableStateFlow(level.hints.find { it.strength == Strength.MEDIUM }!!.toHintElementState())
    val strongHintState = MutableStateFlow(level.hints.find { it.strength == Strength.STRONG }!!.toHintElementState())

    fun onAction(action: GameScreenActions) {
        when (action) {
            is GameScreenActions.MakeAGuess -> guessLetter(action.character, action.row)
            is GameScreenActions.ShowHint -> showHint(action.strength)
        }
    }

    private fun showHint(strength: Strength) {
        when (strength) {
            Strength.WEAK -> weakHintState.update { it.copy(isEnabled = false) }
            Strength.MEDIUM -> mediumHintState.update { it.copy(isEnabled = false) }
            Strength.STRONG -> strongHintState.update { it.copy(isEnabled = false) }
        }
    }

    private fun guessLetter(character: Char, row: KeyboardRow) {
        when (row) {
            KeyboardRow.TOP -> {
                val mutableKeys = topRowState.value.toMutableList()
                val key = mutableKeys.find { it.character == character }
                if (key == null) return
                val index = mutableKeys.indexOf(key)
                mutableKeys[index] = key.copy(isEnabled = false)

                topRowState.value = mutableKeys.toList()
            }

            KeyboardRow.MIDDLE -> {
                val mutableKeys = midRowState.value.toMutableList()
                val key = mutableKeys.find { it.character == character }
                if (key == null) return
                val index = mutableKeys.indexOf(key)
                mutableKeys[index] = key.copy(isEnabled = false)

                midRowState.value = mutableKeys.toList()
            }

            KeyboardRow.BOTTOM -> {
                val mutableKeys = bottomRowState.value.toMutableList()
                val key = mutableKeys.find { it.character == character }
                if (key == null) return
                val index = mutableKeys.indexOf(key)
                mutableKeys[index] = key.copy(isEnabled = false)

                bottomRowState.value = mutableKeys.toList()
            }
        }
    }
}