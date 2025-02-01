package com.ns.hangmanhero.state

import com.ns.hangmanhero.data.Stage
import com.ns.hangmanhero.data.Strength
import com.ns.hangmanhero.stages.game_play.data.KeyboardRow

data class GameState(
    val stage: Stage,
    val keyCount: Int,
    val remainingGuesses: Int,
    val clue: String,
    val answer: List<CharacterState>,
    val hints: Map<Strength, HintElementState>,
    val keyboard: Map<KeyboardRow, List<KeyboardKeyState>>
)