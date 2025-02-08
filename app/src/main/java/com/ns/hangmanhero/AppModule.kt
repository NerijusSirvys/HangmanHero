package com.ns.hangmanhero

import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.ns.hangmanhero.data.GameRepository
import com.ns.hangmanhero.data.models.Difficulty
import com.ns.hangmanhero.data.models.Strength
import com.ns.hangmanhero.viewModel.GameViewmodel
import data.HintEntity
import data.LevelEntity
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::GameViewmodel)
    singleOf(::GameService)
    singleOf(::GameRepository)
    single<SqlDriver> { AndroidSqliteDriver(HangmanHeroDbContext.Schema, get(), "HangmanHero.db") }
    single {
        HangmanHeroDbContext(
            get(),
            LevelEntityAdapter = LevelEntity.Adapter(
                difficultyAdapter = EnumColumnAdapter<Difficulty>()
            ),
            HintEntityAdapter = HintEntity.Adapter(
                strengthAdapter = EnumColumnAdapter<Strength>()
            )
        )
    }
}