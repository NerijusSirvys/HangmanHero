package com.ns.hangmanhero.viewModel.models

import com.ns.hangmanhero.data.models.Level
import com.ns.hangmanhero.data.models.Player

data class NewLoadMap(
    val playerData: Player,
    val levelData: Level?
)
