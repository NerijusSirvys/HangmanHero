package com.ns.hangmanhero.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RectangleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.small
    ) {
        ComponentText(text = text)
    }
}