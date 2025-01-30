package com.ns.hangmanhero

import com.ns.hangmanhero.data.Difficulty
import com.ns.hangmanhero.data.Hint
import com.ns.hangmanhero.data.Level
import com.ns.hangmanhero.data.Strength
import com.ns.hangmanhero.screens.game.data.KeyboardRow
import com.ns.hangmanhero.screens.game.states.KeyboardKeyState
import java.util.UUID

class HangmanService {
    fun getKeyboardKeys(row: KeyboardRow): List<KeyboardKeyState> {
        return when(row){
            KeyboardRow.TOP -> "qwertyuiop".map { KeyboardKeyState(it, true) }
            KeyboardRow.MIDDLE -> "asdfghjkl".map { KeyboardKeyState(it, true) }
            KeyboardRow.BOTTOM -> "zxcvbnm".map { KeyboardKeyState(it, true) }
        }
    }

    fun getLevel(difficulty: Difficulty): Level {
        return Level(
            id = UUID.randomUUID().toString(),
            answer = "answer",
            clue = "This is my random clue",
            difficulty = Difficulty.EASY,
            hints = listOf(
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint one",
                    strength = Strength.WEAK
                ),
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint two",
                    strength = Strength.MEDIUM
                ),
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint three",
                    strength = Strength.STRONG
                )
            )
        )
    }
}