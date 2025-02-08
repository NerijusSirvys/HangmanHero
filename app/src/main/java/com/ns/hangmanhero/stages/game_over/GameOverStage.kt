package com.ns.hangmanhero.stages.game_over

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ns.hangmanhero.actions.GameActions

@Composable
fun GameOverScreen(
    onAction: (GameActions) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Game Over",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            OutlinedButton(
                onClick = { onAction(GameActions.Restart) },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Retry")
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
