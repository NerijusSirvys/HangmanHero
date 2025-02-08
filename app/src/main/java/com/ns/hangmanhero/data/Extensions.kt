package com.ns.hangmanhero.data

import com.ns.hangmanhero.data.models.Hint
import com.ns.hangmanhero.data.models.Level
import com.ns.hangmanhero.data.models.Player
import data.GetLevels
import data.PlayerEntity

fun PlayerEntity.mapToPlayer(): Player {
    return Player(
        id = this.id,
        keys = this.keys.toInt(),
        completeLevels = this.completedLevels.toInt()
    )
}

fun List<GetLevels>.mapGetLevelsToLevels(): List<Level> {
    return this.groupBy { it.levelId }.map { map ->
        val firstHint = map.value.first()
        Level(
            id = map.key,
            clue = firstHint.clue,
            answer = firstHint.answer,
            difficulty = firstHint.difficulty,
            isCompleted = firstHint.isCompleted,
            hints = map.value.map {
                Hint(
                    id = it.hintId,
                    text = it.text,
                    strength = it.strength,
                )
            }
        )
    }
}