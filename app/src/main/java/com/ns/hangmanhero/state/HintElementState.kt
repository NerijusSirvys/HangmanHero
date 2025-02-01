package com.ns.hangmanhero.state

data class HintElementState(
    val id: String,
    val text: String,
    val isEnabled: Boolean,
    val cost: Int
)
