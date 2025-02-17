package com.ns.hangmanhero.stages.game_play.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ns.hangmanhero.R
import com.ns.hangmanhero.components.Container
import com.ns.hangmanhero.ui.theme.HangmanHeroTheme

@Composable
fun HintElement(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    keyCount: Int = 1,
    text: String,
    show: Boolean
) {
    Container(
        enabled = !show,
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 15.dp,
                vertical = 7.dp
            )
        ) {
            Box {
                if (show) {
                    Text(
                        modifier = modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        text = text
                    )
                } else {
                    Row {
                        for (count in 0..keyCount) {
                            Icon(
                                painter = painterResource(R.drawable.icon_key),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                            )
                        }
                    }
                    Text(
                        modifier = modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Show Hint",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HintElementPreview() {
    HangmanHeroTheme {
        Surface {
            HintElement(
                keyCount = 3,
                text = "Some random text that should be in the hint",
                show = true,
                onClick = {}
            )
        }
    }
}