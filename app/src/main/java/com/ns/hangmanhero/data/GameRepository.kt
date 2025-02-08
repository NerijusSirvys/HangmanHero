package com.ns.hangmanhero.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.ns.hangmanhero.HangmanHeroDbContext
import com.ns.hangmanhero.data.models.Level
import com.ns.hangmanhero.data.models.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext

class GameRepository(
    private val context: HangmanHeroDbContext
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getLevels(completed: Boolean): Flow<List<Level>> {
        return context.levelEntityQueries.getLevels(completed)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .mapLatest { it.mapGetLevelsToLevels() }
    }

    suspend fun updateLevelStage(completed: Boolean, levelId: String) {
        withContext(Dispatchers.IO) {
            context.levelEntityQueries.updateLevelStage(completed, levelId)
        }
    }

    suspend fun updatePlayerState(keys: Long, completedLevels: Long, playerId: String) {
        withContext(Dispatchers.IO) {
            context.playerEntityQueries.updatePlayer(keys, completedLevels, playerId)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getPlayer(playerId: String): Flow<Player> {
        return context.playerEntityQueries.getPlayer(playerId)
            .asFlow()
            .mapToOne(Dispatchers.IO)
            .mapLatest { it.mapToPlayer() }
    }

    suspend fun resetData(playerId: String) {
        withContext(Dispatchers.IO) {
            context.transaction {
                context.levelEntityQueries.updateAllLevelStage(false)
                context.playerEntityQueries.updatePlayer(0, 0, playerId)
            }
        }
    }
}