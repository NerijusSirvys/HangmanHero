package com.ns.hangmanhero.navigation

import kotlinx.serialization.Serializable

class Destinations {

    @Serializable
    object GameScreen

    @Serializable
    object NextLevelScreen

    @Serializable
    object GameOverScreen
}
