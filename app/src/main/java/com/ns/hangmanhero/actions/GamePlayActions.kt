package com.ns.hangmanhero.actions

import com.ns.hangmanhero.data.Strength
import com.ns.hangmanhero.stages.game_play.data.KeyboardRow

sealed interface GamePlayActions {
    data class MakeAGuess(val character: Char, val row: KeyboardRow) : GamePlayActions
    data class ShowHint(val strength: Strength) : GamePlayActions
}