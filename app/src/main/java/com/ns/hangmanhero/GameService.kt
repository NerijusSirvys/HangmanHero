package com.ns.hangmanhero

import com.ns.hangmanhero.data.Difficulty
import com.ns.hangmanhero.data.Hint
import com.ns.hangmanhero.data.Level
import com.ns.hangmanhero.data.LevelStage
import com.ns.hangmanhero.data.Strength
import com.ns.hangmanhero.state.GameState
import java.util.UUID

class GameService {

    private var _levels = mutableListOf(
        Level(
            id = UUID.randomUUID().toString(),
            answer = "answer",
            clue = "This is my random clue LV1 easy",
            difficulty = Difficulty.EASY,
            stage = LevelStage.TO_BE_DONE,
            hints = listOf(
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint one 1",
                    strength = Strength.WEAK
                ),
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint two 1",
                    strength = Strength.MEDIUM
                ),
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint three 1",
                    strength = Strength.STRONG
                )
            )
        ),
        Level(
            id = UUID.randomUUID().toString(),
            answer = "answer",
            clue = "This is my random clue LV2 easy",
            difficulty = Difficulty.EASY,
            stage = LevelStage.TO_BE_DONE,
            hints = listOf(
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint one 2",
                    strength = Strength.WEAK
                ),
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint two 2",
                    strength = Strength.MEDIUM
                ),
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint three 2",
                    strength = Strength.STRONG
                )
            )
        ),
        Level(
            id = UUID.randomUUID().toString(),
            answer = "answer",
            clue = "This is my random clue LV3 medium",
            difficulty = Difficulty.MEDIUM,
            stage = LevelStage.TO_BE_DONE,
            hints = listOf(
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint one 3",
                    strength = Strength.WEAK
                ),
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint two 3",
                    strength = Strength.MEDIUM
                ),
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint three 3",
                    strength = Strength.STRONG
                )
            )
        ),
        Level(
            id = UUID.randomUUID().toString(),
            answer = "answer",
            clue = "This is my random clue LV4 medium",
            difficulty = Difficulty.MEDIUM,
            stage = LevelStage.TO_BE_DONE,
            hints = listOf(
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint one 4",
                    strength = Strength.WEAK
                ),
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint two 4",
                    strength = Strength.MEDIUM
                ),
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint three 4",
                    strength = Strength.STRONG
                )
            )
        ),
        Level(
            id = UUID.randomUUID().toString(),
            answer = "answer",
            clue = "This is my random clue LV5 hard",
            difficulty = Difficulty.HARD,
            stage = LevelStage.TO_BE_DONE,
            hints = listOf(
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint one 5",
                    strength = Strength.WEAK
                ),
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint two 5",
                    strength = Strength.MEDIUM
                ),
                Hint(
                    id = UUID.randomUUID().toString(),
                    text = "Supper hint three 5",
                    strength = Strength.STRONG
                )
            )
        )
    )

    fun saveState(state: GameState) {
        // TODO: SAVE STATE TO DISK (JSON?)
    }

    fun getLevel(difficulty: Difficulty): Level {
        return _levels[0]
    }

    fun getNewLevel(difficulty: Difficulty, persistDifficulty: Boolean): Level? {
        _levels = _levels.map {
            if (it.stage == LevelStage.STARTED)
                it.stage = LevelStage.TO_BE_DONE
            it
        }.toMutableList()

        var newLevel: Level? = null

        if (persistDifficulty) {
            newLevel = _levels
                .filter { it.difficulty == difficulty && it.stage != LevelStage.COMPLETED }
                .random()
        } else {
            val potentialLevels = _levels.filter { it.difficulty == difficulty && it.stage != LevelStage.COMPLETED }
            if (potentialLevels.isNotEmpty()) {
                newLevel = potentialLevels.random()
            } else {
                if (difficulty != Difficulty.VERY_HARD) {
                    val nextDifficulty = Difficulty.entries[(difficulty.ordinal + 1)]
                    val potentialLevels = _levels.filter { it.difficulty == nextDifficulty && it.stage != LevelStage.COMPLETED }
                    if (potentialLevels.isNotEmpty()) {
                        newLevel = potentialLevels.random()
                    }
                }
            }
        }

        newLevel?.let { it.stage = LevelStage.STARTED }

        return newLevel
    }
}