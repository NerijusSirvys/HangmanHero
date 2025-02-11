package com.ns.hangmanhero.stages.game_play.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ns.hangmanhero.components.ComponentText
import com.ns.hangmanhero.ui.theme.HangmanHeroTheme

@Composable
fun KeyboardKey(
    modifier: Modifier = Modifier,
    onClick: (key: Char) -> Unit,
    enabled: Boolean,
    character: Char
) {
    OutlinedCard(
        modifier = modifier.height(IntrinsicSize.Min),
        onClick = { onClick.invoke(character) },
        enabled = enabled,
        shape = MaterialTheme.shapes.small
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ComponentText(
                text = character.lowercase(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
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