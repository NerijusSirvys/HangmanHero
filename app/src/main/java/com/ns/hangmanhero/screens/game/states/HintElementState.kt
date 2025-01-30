package com.ns.hangmanhero.screens.game.states

data class HintElementState(
    val id: String,
    val text: String,
    val isEnabled: Boolean,
    val cost: Int
)
