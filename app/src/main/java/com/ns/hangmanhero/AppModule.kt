package com.ns.hangmanhero

import com.ns.hangmanhero.screens.game.GameScreenViewmodel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::GameScreenViewmodel)
    factoryOf(::HangmanService)
}