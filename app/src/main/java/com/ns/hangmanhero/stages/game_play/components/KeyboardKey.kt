package com.ns.hangmanhero.stages.game_play.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ns.hangmanhero.components.Container
import com.ns.hangmanhero.ui.theme.HangmanHeroTheme

@Composable
fun KeyboardKey(
    modifier: Modifier = Modifier,
    onClick: (key: Char) -> Unit,
    enabled: Boolean,
    character: Char
) {
    Container(
        modifier = modifier,
        onClick = { onClick.invoke(character) },
        enabled = enabled,
    ) {
        Text(
            modifier = Modifier.padding(7.dp),
            text = character.lowercase(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
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