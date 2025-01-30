package com.ns.hangmanhero.screens.game

import androidx.lifecycle.ViewModel
import com.ns.hangmanhero.HangmanService
import com.ns.hangmanhero.screens.game.data.KeyboardRow
import kotlinx.coroutines.flow.MutableStateFlow

class GameScreenViewmodel(
    private val service: HangmanService
) : ViewModel() {

    val topRowState = MutableStateFlow(service.getKeyboardKeys(KeyboardRow.TOP))
    val midRowState = MutableStateFlow(service.getKeyboardKeys(KeyboardRow.MIDDLE))
    val bottomRowState = MutableStateFlow(service.getKeyboardKeys(KeyboardRow.BOTTOM))

    fun onAction(action: GameScreenActions) {
        when (action) {
            is GameScreenActions.MakeAGuess -> guessLetter(action.character, action.row)
            is GameScreenActions.ShowHint -> showHint(action.id)
        }
    }

    private fun showHint(id: String) {
        TODO("Not yet implemented")
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