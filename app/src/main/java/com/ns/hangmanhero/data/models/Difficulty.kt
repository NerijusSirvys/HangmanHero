package com.ns.hangmanhero.data.models

import kotlinx.serialization.Serializable

@Serializable
enum class Difficulty {
    EASY,
    MEDIUM,
    HARD,
    VERY_HARD
}