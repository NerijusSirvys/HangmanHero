package com.ns.hangmanhero.state

import com.ns.hangmanhero.data.models.Strength
import com.ns.hangmanhero.stages.game_play.data.KeyboardRow
import com.ns.hangmanhero.viewModel.models.Stage

data class GameState(
    val isLoading: Boolean = true,
    val stage: Stage = Stage.Game,
    val keyCount: Int = 0,
    val remainingGuesses: Int = 0,
    val clue: String = "",
    val completeLevels: Int = 0,
    val answer: List<CharacterState> = listOf(),
    val hints: Map<Strength, HintElementState> = mapOf(),
    val keyboard: Map<KeyboardRow, List<KeyboardKeyState>> = mapOf()
)