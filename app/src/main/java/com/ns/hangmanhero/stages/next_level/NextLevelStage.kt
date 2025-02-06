package com.ns.hangmanhero.stages.next_level

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ns.hangmanhero.actions.GameActions

@Composable
fun NextLevelScreen(
    onAction: (GameActions) -> Unit
) {

    LaunchedEffect(Unit) {
        onAction(GameActions.SaveState)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Next Level",
            textAlign = TextAlign.Center
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            OutlinedButton(
                onClick = { onAction(GameActions.NextLevel) },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Next")
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