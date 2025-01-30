package com.ns.hangmanhero.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ns.hangmanhero.screens.game.components.KeyboardKeyRow
import com.ns.hangmanhero.screens.game.data.KeyboardRow
import com.ns.hangmanhero.screens.game.states.KeyboardKeyState

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    topRowKeys: List<KeyboardKeyState>,
    middleRowKeys: List<KeyboardKeyState>,
    bottomRowKeys: List<KeyboardKeyState>,
    onActions: (GameScreenActions) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            KeyboardKeyRow(keys = topRowKeys, onClick = { onActions.invoke(GameScreenActions.MakeAGuess(it, KeyboardRow.TOP)) })
            KeyboardKeyRow(keys = middleRowKeys, onClick = { onActions.invoke(GameScreenActions.MakeAGuess(it, KeyboardRow.MIDDLE)) })
            KeyboardKeyRow(keys = bottomRowKeys, onClick = { onActions.invoke(GameScreenActions.MakeAGuess(it, KeyboardRow.BOTTOM)) })
        }
    }
}

