package com.example.ui.theme

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

/**
 * Centralized motion tokens so every screen animates with the same feel:
 * quick, springy and never sluggish. Reuse these instead of ad-hoc durations.
 */
object Motion {
    val Snappy = spring<Float>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessMedium
    )
    val Gentle = spring<Float>(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessLow
    )
    val FastTween = tween<Float>(durationMillis = 180)
    val MediumTween = tween<Float>(durationMillis = 320)
}

/**
 * Adds a subtle, modern "press to shrink" tactile response to any tappable
 * surface (cards, pills, icon buttons) without altering its click behaviour.
 * Purely visual — wrap the existing `Modifier.clickable { ... }` with this.
 */
@Composable
fun Modifier.bounceClick(
    interactionSource: MutableInteractionSource? = null,
    scaleDown: Float = 0.94f
): Modifier {
    val source = interactionSource ?: remember { MutableInteractionSource() }
    val isPressed by source.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) scaleDown else 1f,
        animationSpec = Motion.Snappy,
        label = "bounceClickScale"
    )
    return this.graphicsLayer {
        scaleX = scale
        scaleY = scale
    }
}
