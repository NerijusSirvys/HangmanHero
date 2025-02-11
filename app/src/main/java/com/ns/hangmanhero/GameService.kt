package com.ns.hangmanhero

import com.ns.hangmanhero.data.GameRepository
import com.ns.hangmanhero.data.models.Difficulty
import com.ns.hangmanhero.data.models.Level
import com.ns.hangmanhero.data.models.Player
import com.ns.hangmanhero.state.GameState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameService(
    private val repository: GameRepository
) {

    suspend fun saveState(state: GameState, playerId: String) {
        repository.updatePlayerState(state.keyCount.toLong(), state.completeLevels.toLong(), playerId)
    }

    suspend fun completeLevel(levelId: String) {
        repository.updateLevelStage(true, levelId)
    }

    fun getRandomLevel(difficulty: Difficulty): Flow<Level?> {
        return repository.getLevels(false).map { levels ->
            selectRandom(levels, difficulty)
        }
    }

    fun loadPlayerData(playerId: String): Flow<Player> {
        return repository.getPlayer(playerId)
    }

    private fun selectRandom(levels: List<Level>, difficulty: Difficulty): Level? {
        var newLevel: Level? = null

        val potentialLevels = levels.filter { it.difficulty == difficulty }
        if (potentialLevels.isNotEmpty()) {
            newLevel = potentialLevels.random()
        } else {
            if (difficulty != Difficulty.VERY_HARD) {
                val nextDifficulty = Difficulty.entries[(difficulty.ordinal + 1)]
                return selectRandom(levels, nextDifficulty)
            }
        }
        return newLevel
    }

    suspend fun resetData(playerId: String) {
        repository.resetData(playerId)
    }

    suspend fun populateData(levels: List<Level>) {
        repository.populateGameData(levels)
    }
}