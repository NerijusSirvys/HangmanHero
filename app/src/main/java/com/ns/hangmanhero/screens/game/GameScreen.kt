package com.ns.hangmanhero.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ns.hangmanhero.data.Strength
import com.ns.hangmanhero.screens.game.components.HintElement
import com.ns.hangmanhero.screens.game.components.KeyboardKeyRow
import com.ns.hangmanhero.screens.game.data.KeyboardRow
import com.ns.hangmanhero.screens.game.states.HintElementState
import com.ns.hangmanhero.screens.game.states.KeyboardKeyState

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    topRowKeys: List<KeyboardKeyState>,
    middleRowKeys: List<KeyboardKeyState>,
    bottomRowKeys: List<KeyboardKeyState>,
    weakHint: HintElementState,
    mediumHint: HintElementState,
    strongHint: HintElementState,
    onActions: (GameScreenActions) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Column {
                HintElement(
                    keyCount = weakHint.cost,
                    text = weakHint.text,
                    show = !weakHint.isEnabled,
                    onClick = { onActions.invoke(GameScreenActions.ShowHint(Strength.WEAK)) })
                HintElement(
                    keyCount = mediumHint.cost,
                    text = mediumHint.text,
                    show = !mediumHint.isEnabled,
                    onClick = { onActions.invoke(GameScreenActions.ShowHint(Strength.MEDIUM)) })
                HintElement(
                    keyCount = strongHint.cost,
                    text = strongHint.text,
                    show = !strongHint.isEnabled,
                    onClick = { onActions.invoke(GameScreenActions.ShowHint(Strength.STRONG)) })
            }

            Spacer(modifier.height(25.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                KeyboardKeyRow(
                    keys = topRowKeys,
                    onClick = { onActions.invoke(GameScreenActions.MakeAGuess(it, KeyboardRow.TOP)) })
                KeyboardKeyRow(
                    keys = middleRowKeys,
                    onClick = { onActions.invoke(GameScreenActions.MakeAGuess(it, KeyboardRow.MIDDLE)) })
                KeyboardKeyRow(
                    keys = bottomRowKeys,
                    onClick = { onActions.invoke(GameScreenActions.MakeAGuess(it, KeyboardRow.BOTTOM)) })
            }
        }
    }
}

