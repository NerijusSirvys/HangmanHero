package com.ns.hangmanhero.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Container(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)),
        shape = MaterialTheme.shapes.small,
        content = content
    )
}

@Composable
fun Container(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)),
        shape = MaterialTheme.shapes.small,
        content = content
    )
}