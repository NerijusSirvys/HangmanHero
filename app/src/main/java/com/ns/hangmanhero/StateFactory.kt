package com.ns.hangmanhero

import com.ns.hangmanhero.data.Hint
import com.ns.hangmanhero.data.Strength
import com.ns.hangmanhero.mappers.toHintElementState
import com.ns.hangmanhero.screens.game.data.KeyboardRow
import com.ns.hangmanhero.screens.game.states.CharacterState
import com.ns.hangmanhero.screens.game.states.HintElementState
import com.ns.hangmanhero.screens.game.states.KeyboardKeyState

class StateFactory {
    companion object {
        fun createKeyboardKeyState(row: KeyboardRow): List<KeyboardKeyState> {
            return when (row) {
                KeyboardRow.TOP -> "qwertyuiop".map { KeyboardKeyState(it, true) }
                KeyboardRow.MIDDLE -> "asdfghjkl".map { KeyboardKeyState(it, true) }
                KeyboardRow.BOTTOM -> "zxcvbnm".map { KeyboardKeyState(it, true) }
            }
        }

        fun createHintState(hints: List<Hint>, strength: Strength): HintElementState {
            return hints.find { it.strength == strength }!!.toHintElementState()
        }

        fun createAnswerState(answer: String): List<CharacterState> {
            val state = answer.map { CharacterState(it, false) }.toMutableList()
            val keyLocations = calculateKeyLocations(answer.length)

            keyLocations.forEach { index ->
                state[index] = state[index].copy(keyHolder = true)
            }

            return state.toList()
        }

        private fun calculateKeyLocations(answerSize: Int): List<Int> {
            val indexes = (0..<answerSize).toMutableList()

            val first = indexes.random()
            indexes.remove(first)

            val second = indexes.random()
            indexes.remove(second)

            val third = indexes.random()

            return listOf(first, second, third)
        }
    }
}
