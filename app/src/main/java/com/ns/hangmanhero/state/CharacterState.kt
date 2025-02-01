package com.ns.hangmanhero.state

data class CharacterState(
    val letter: Char,
    val keyHolder: Boolean,
    val show: Boolean = false,
)
