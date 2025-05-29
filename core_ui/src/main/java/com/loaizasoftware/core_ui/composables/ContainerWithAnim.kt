package com.loaizasoftware.core_ui.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha


/**
 * A container that wraps its content and applies a fade-in animation
 * when it enters the composition.
 *
 * ## Behavior:
 * - The content starts fully transparent.
 * - After entering the composition, the content smoothly fades in over 500 milliseconds.
 *
 * ## Usage:
 * Wrap any composable inside this container to have it fade in on load.
 *
 * ### Example:
 * ```
 * ContainerWithAnim {
 *     Text("Welcome to the app!")
 * }
 * ```
 *
 * @param content The composable content to be displayed with the fade-in animation.
 */
@Composable
fun ContainerWithAnim(durationMillis: Int = 500, content: @Composable () -> Unit) {

    val visible = remember { mutableStateOf(false) }

    // Animate alpha from 0f to 1f
    val alpha by animateFloatAsState(
        targetValue = if (visible.value) 1f else 0f,
        animationSpec = tween(durationMillis = durationMillis)
    )

    // Trigger fade-in once when this Composable enters composition
    LaunchedEffect(Unit) {
        visible.value = true
    }

    Box(modifier = Modifier.alpha(alpha)) {
        content()
    }

}