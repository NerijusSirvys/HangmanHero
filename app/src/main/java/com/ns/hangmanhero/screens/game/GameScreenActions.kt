package com.ns.hangmanhero.screens.game

import com.ns.hangmanhero.screens.game.data.KeyboardRow

sealed interface GameScreenActions {
    data class MakeAGuess(val character: Char, val row: KeyboardRow) : GameScreenActions
    data class ShowHint(val id: String) : GameScreenActions
}