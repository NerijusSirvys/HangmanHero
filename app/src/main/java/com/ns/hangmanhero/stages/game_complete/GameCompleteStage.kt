package com.ns.hangmanhero.stages.game_complete

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ns.hangmanhero.actions.GameActions

@Composable
fun GameCompleteStage(
    modifier: Modifier = Modifier,
    onAction: (GameActions) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(25.dp)) {
        Text(
            text = "Looks like this it it. No more levels left. CONGRATULATIONS...",
            textAlign = TextAlign.Center,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            OutlinedButton(
                onClick = { onAction(GameActions.StartAgain) },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Start again")
            }

            OutlinedButton(
                onClick = { onAction(GameActions.Exit) },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Exit")
            }
        }
    }
}