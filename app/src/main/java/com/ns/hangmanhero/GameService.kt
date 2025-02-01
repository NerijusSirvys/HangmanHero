package com.ns.hangmanhero

import com.ns.hangmanhero.data.Difficulty
import com.ns.hangmanhero.data.Hint
import com.ns.hangmanhero.data.Level
import com.ns.hangmanhero.data.Strength
import java.util.UUID

class GameService {


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