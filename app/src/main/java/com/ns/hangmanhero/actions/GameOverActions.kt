package com.ns.hangmanhero.actions

sealed interface GameOverActions {
    data object Restart : GameOverActions
    data object Exit : GameOverActions
}