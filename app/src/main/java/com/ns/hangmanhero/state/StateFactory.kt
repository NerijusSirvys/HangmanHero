package com.ns.hangmanhero.state

import com.ns.hangmanhero.data.Hint
import com.ns.hangmanhero.data.Level
import com.ns.hangmanhero.data.Stage
import com.ns.hangmanhero.data.Strength
import com.ns.hangmanhero.data.TransferableState
import com.ns.hangmanhero.mappers.toHintElementState
import com.ns.hangmanhero.stages.game_play.data.KeyboardRow

class StateFactory {
    companion object {
        fun createGameState(level: Level, transfer: TransferableState): GameState {
            return GameState(
                stage = Stage.Game,
                keyCount = transfer.keys,
                remainingGuesses = transfer.remainingGuesses,
                clue = level.clue,
                currentLevel = level.id,
                completeLevels = transfer.completeLevels,
                answer = createAnswerState(level.answer),
                hints = mapOf(
                    Strength.WEAK to createHintState(level.hints, Strength.WEAK),
                    Strength.MEDIUM to createHintState(level.hints, Strength.MEDIUM),
                    Strength.STRONG to createHintState(level.hints, Strength.STRONG),
                ),
                keyboard = mapOf(
                    KeyboardRow.TOP to createKeyboardKeyState(KeyboardRow.TOP),
                    KeyboardRow.MIDDLE to createKeyboardKeyState(KeyboardRow.MIDDLE),
                    KeyboardRow.BOTTOM to createKeyboardKeyState(KeyboardRow.BOTTOM),
                )
            )
        }

        private fun createKeyboardKeyState(row: KeyboardRow): List<KeyboardKeyState> {
            return when (row) {
                KeyboardRow.TOP -> "qwertyuiop".map { KeyboardKeyState(it, true) }
                KeyboardRow.MIDDLE -> "asdfghjkl".map { KeyboardKeyState(it, true) }
                KeyboardRow.BOTTOM -> "zxcvbnm".map { KeyboardKeyState(it, true) }
            }
        }

        private fun createHintState(hints: List<Hint>, strength: Strength): HintElementState {
            return hints.find { it.strength == strength }!!.toHintElementState()
        }

        private fun createAnswerState(answer: String): List<CharacterState> {
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
