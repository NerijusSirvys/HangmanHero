package com.ns.hangmanhero.data

data class TransferableState(
    val keys: Int = 0,
    val remainingGuesses: Int = 6,
    val completeLevels: List<String> = emptyList()
)
