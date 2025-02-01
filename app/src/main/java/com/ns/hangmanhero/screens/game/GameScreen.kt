package com.ns.hangmanhero.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ns.hangmanhero.screens.game.components.CharacterElement
import com.ns.hangmanhero.screens.game.components.HintElement
import com.ns.hangmanhero.screens.game.components.KeyboardKeyRow
import com.ns.hangmanhero.screens.game.states.GameScreenState

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    state: GameScreenState,
    onActions: (GameScreenActions) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = state.clue,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

            Spacer(modifier.height(25.dp))

            Row {
                state.answer.forEach {
                    CharacterElement(
                        modifier = Modifier.size(40.dp),
                        character = it.letter,
                        show = it.show,
                        isKeyholder = it.keyHolder,
                    )
                }
            }

            Spacer(modifier.height(25.dp))

            Column {
                state.hints.forEach { (k, v) ->
                    HintElement(
                        keyCount = v.cost,
                        text = v.text,
                        show = !v.isEnabled,
                        onClick = { onActions(GameScreenActions.ShowHint(k)) }
                    )
                }
            }

            Spacer(modifier.height(25.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                state.keyboard.forEach { (k, v) ->
                    KeyboardKeyRow(
                        keys = v,
                        onClick = { onActions.invoke(GameScreenActions.MakeAGuess(it, k)) })
                }
            }
        }
    }
}

