package com.ns.hangmanhero.screens.game.states

import com.ns.hangmanhero.data.Strength
import com.ns.hangmanhero.screens.game.data.KeyboardRow

data class GameScreenState(
    val keyCount: Int,
    val clue: String,
    val answer: List<CharacterState>,
    val hints: Map<Strength, HintElementState>,
    val keyboard: Map<KeyboardRow, List<KeyboardKeyState>>
)