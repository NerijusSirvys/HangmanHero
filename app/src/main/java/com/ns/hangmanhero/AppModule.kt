package com.ns.hangmanhero

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::GameViewmodel)
    factoryOf(::GameService)
}