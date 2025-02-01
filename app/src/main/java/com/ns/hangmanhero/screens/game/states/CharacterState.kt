package com.ns.hangmanhero.screens.game.states

data class CharacterState(
    val letter: Char,
    val keyHolder: Boolean,
    val show: Boolean = false,
)
