package com.loaizasoftware.core_ui.general

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    isVisible: Boolean = true // Pass visibility control from outside
) {
    // Animate alpha based on `isVisible`
    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 500) // 👈 Fade duration
    )

    // Only render loader when it's still visible (even if fading out)
    if (alpha > 0f) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 1f))
                .alpha(alpha),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(color = Color.Blue)
        }
    }
}
