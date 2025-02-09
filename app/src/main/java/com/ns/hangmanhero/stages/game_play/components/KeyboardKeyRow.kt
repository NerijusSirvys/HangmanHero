package com.ns.hangmanhero.stages.game_play.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ns.hangmanhero.state.KeyboardKeyState

@Composable
fun KeyboardKeyRow(
    modifier: Modifier = Modifier,
    onClick: (key: Char) -> Unit,
    keys: List<KeyboardKeyState>
) {
    val padding = when (keys.size) {
        10 -> 0f
        9 -> 0.5f
        else -> 1.5f
    }

    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        if (padding > 0.0)
            Spacer(modifier.weight(padding))

        keys.forEach {
            KeyboardKey(
                modifier = Modifier
                    .weight(1f),
                onClick = { key -> onClick.invoke(key) },
                enabled = it.isEnabled,
                character = it.character
            )
        }

        if (padding > 0.0)
            Spacer(modifier.weight(padding))
    }
}