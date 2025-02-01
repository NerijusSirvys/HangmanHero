package com.ns.hangmanhero.stages.game_play.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ns.hangmanhero.R
import com.ns.hangmanhero.ui.theme.HangmanHeroTheme

@Composable
fun InfoTab(
    @DrawableRes iconId: Int,
    value: Int,
) {
    Surface(
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 3.dp)
                .padding(end = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Icon(
                painter = painterResource(iconId),
                contentDescription = null,
            )
            Text(
                text = value.toString(),
                fontSize = 26.sp
            )
        }
    }
}

@Preview
@Composable
private fun InfoTabPreview() {
    HangmanHeroTheme {
        Surface {
            InfoTab(
                iconId = R.drawable.icon_key,
                value = 999
            )
        }
    }
}