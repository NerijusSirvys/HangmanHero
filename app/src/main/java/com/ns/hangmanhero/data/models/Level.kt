package com.ns.hangmanhero.data.models

data class Level(
    val id: String,
    val clue: String,
    val answer: String,
    val hints: List<Hint>,
    val difficulty: Difficulty,
    var isCompleted: Boolean
)

