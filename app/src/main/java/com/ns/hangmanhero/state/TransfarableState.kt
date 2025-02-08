package com.ns.hangmanhero.state

data class TransferableState(
    val keys: Int = 0,
    val remainingGuesses: Int = 6,
    val completeLevels: Int = 0
)
