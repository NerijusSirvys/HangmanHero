package com.ns.hangmanhero.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import kotlinx.coroutines.launch

@Composable
fun ComponentText(
   modifier: Modifier = Modifier,
   text: String,
   scrollable: Boolean = false,
   textAlign: TextAlign = TextAlign.Unspecified,
   fontSize: TextUnit = TextUnit.Unspecified
) {
   var localModifier = modifier
   if (scrollable) {

      val scrollState = rememberScrollState()
      localModifier = modifier.horizontalScroll(scrollState)

      val scrollInteractionState by scrollState.interactionSource.collectIsDraggedAsState()
      val scope = rememberCoroutineScope()

      val endOfScroll by remember {
         derivedStateOf {
            scrollState.value == scrollState.maxValue
         }
      }

      LaunchedEffect(endOfScroll, scrollInteractionState) {
         scope.launch {
            println("Scroll")
            if (!scrollState.isScrollInProgress) {
               println("Reset")
               scrollState.scrollTo(0)
            }
            println("Start animation")
            scrollState.animateScrollTo(
               value = scrollState.maxValue,
               animationSpec = tween(
                  durationMillis = 10000,
                  easing = LinearEasing
               )
            )
         }
      }
   }

   Box(
      contentAlignment = Alignment.CenterStart,
      modifier = localModifier
   ) {
      Text(
         modifier = modifier,
         text = text,
         textAlign = textAlign,
         fontSize = fontSize,
         color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
         overflow = TextOverflow.Visible
      )
   }
}