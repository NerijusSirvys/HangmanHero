package com.ns.hangmanhero.stages.game_over

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ns.hangmanhero.actions.GameActions
import com.ns.hangmanhero.components.RectangleButton
import com.ns.hangmanhero.components.ScreenTitleText

@Composable
fun GameOverScreen(
    onAction: (GameActions) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenTitleText(text = "Game Over")

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            RectangleButton(
                onClick = { onAction(GameActions.Restart) },
                modifier = Modifier.weight(1f),
                text = "Retry"
            )

            RectangleButton(
                onClick = { onAction(GameActions.Exit) },
                modifier = Modifier.weight(1f),
                text = "Exit"
            )
        }
    }
}
