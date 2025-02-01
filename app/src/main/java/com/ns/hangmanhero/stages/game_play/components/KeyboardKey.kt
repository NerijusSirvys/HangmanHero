package com.ns.hangmanhero.stages.game_play.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ns.hangmanhero.ui.theme.HangmanHeroTheme

@Composable
fun KeyboardKey(
    modifier: Modifier = Modifier,
    onClick: (key: Char) -> Unit,
    enabled: Boolean,
    character: Char
) {
    OutlinedCard(
        modifier = modifier.size(36.dp),
        onClick = { onClick.invoke(character) },
        enabled = enabled
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                text = character.lowercase(),
                fontSize = 20.sp
            )
        }
    }
}

@Preview
@Composable
private fun KeyboardKeyPreview() {
    HangmanHeroTheme {
        Surface {
            KeyboardKey(
                onClick = {},
                enabled = true,
                character = 'a'
            )
        }
    }
}