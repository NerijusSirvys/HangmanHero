package com.ns.hangmanhero.screens.game.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ns.hangmanhero.screens.game.states.KeyboardKeyState

@Composable
fun KeyboardKeyRow(
    modifier: Modifier = Modifier,
    onClick: (key: Char) -> Unit,
    keys: List<KeyboardKeyState>
) {
    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        keys.forEach {
            KeyboardKey(
                onClick = { key -> onClick.invoke(key) },
                enabled = it.isEnabled,
                character = it.character
            )
        }
    }
}