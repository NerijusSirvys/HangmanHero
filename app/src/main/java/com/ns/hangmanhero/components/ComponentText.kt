package com.ns.hangmanhero.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

@Composable
fun ComponentText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        fontSize = fontSize,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
    )
}