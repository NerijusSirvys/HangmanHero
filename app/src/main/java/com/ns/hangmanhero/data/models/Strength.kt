package com.ns.hangmanhero.data.models

import kotlinx.serialization.Serializable

@Serializable
enum class Strength(val value: Int) {
    WEAK(1),
    MEDIUM(2),
    STRONG(3)
}