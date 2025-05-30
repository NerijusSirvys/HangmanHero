package com.ns.hangmanhero.actions

import com.ns.hangmanhero.data.models.Strength
import com.ns.hangmanhero.stages.game_play.data.KeyboardRow

sealed interface GameActions {
    data class MakeAGuess(val character: Char, val row: KeyboardRow) : GameActions
    data class ShowHint(val strength: Strength) : GameActions
    data object Restart : GameActions
    data object StartAgain : GameActions
    data object NextLevel : GameActions
    data object Exit : GameActions
}