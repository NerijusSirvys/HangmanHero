package com.ns.hangmanhero.screens.game.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ns.hangmanhero.R
import com.ns.hangmanhero.ui.theme.HangmanHeroTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CharacterElement(
    modifier: Modifier = Modifier,
    character: Char,
    show: Boolean,
    isKeyholder: Boolean
) {
    val alphaAnim = remember { Animatable(0f) }
    val offsetAnim = remember { Animatable(0f) }

    LaunchedEffect(show) {
        if (show && isKeyholder) {
            launch {
                alphaAnim.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = 500)
                )
            }
            launch {
                offsetAnim.animateTo(
                    targetValue = -90f,
                    animationSpec = tween(durationMillis = 850)
                )
            }
            delay(300)
            alphaAnim.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 500)
            )
        } else {
            alphaAnim.snapTo(0f)
            offsetAnim.snapTo(0f)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(2.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            ),
    ) {
        if (show) {
            Box(contentAlignment = Alignment.Center) {
                if (isKeyholder) {
                    Icon(
                        modifier = Modifier
                            .graphicsLayer {
                                alpha = alphaAnim.value
                                translationY = offsetAnim.value
                            },
                        painter = painterResource(R.drawable.icon_key),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground.copy(0.6f)
                    )
                }
                Text(
                    text = character.toString(),
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            Icon(
                painter = painterResource(R.drawable.icon_question_mark),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground.copy(0.6f)
            )
        }
    }
}

@Preview
@Composable
private fun CharacterElementPreview() {
    HangmanHeroTheme {
        Surface {
            CharacterElement(
                character = 'a',
                show = false,
                isKeyholder = false,
                modifier = Modifier.size(40.dp),
            )
        }
    }
}