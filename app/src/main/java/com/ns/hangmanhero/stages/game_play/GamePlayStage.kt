package com.ns.hangmanhero.stages.game_play

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ns.hangmanhero.actions.GameActions
import com.ns.hangmanhero.components.ComponentText
import com.ns.hangmanhero.stages.game_play.components.CharacterElement
import com.ns.hangmanhero.stages.game_play.components.HintElement
import com.ns.hangmanhero.stages.game_play.components.KeyboardKeyRow
import com.ns.hangmanhero.state.GameState

@Composable
fun GamePlayStage(
    state: GameState,
    onAction: (GameActions) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ComponentText(
            text = state.clue,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
        )

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

        Column {
            state.hints.forEach { (k, v) ->
                HintElement(
                    keyCount = v.cost,
                    text = v.text,
                    show = !v.isEnabled,
                    onClick = { onAction(GameActions.ShowHint(k)) }
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            state.keyboard.forEach { (k, v) ->
                KeyboardKeyRow(
                    keys = v,
                    onClick = { onAction.invoke(GameActions.MakeAGuess(it, k)) })
            }
        }
    }
}
