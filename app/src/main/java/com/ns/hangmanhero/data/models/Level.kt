package com.ns.hangmanhero.data.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Level(
    @Transient
    val id: String = "",
    val clue: String,
    val answer: String,
    val hints: List<Hint>,
    val difficulty: Difficulty,

    @Transient
    var isCompleted: Boolean = false
)