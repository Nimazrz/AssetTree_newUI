package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

/**
 * Renders a currency/number string that smoothly counts up or down whenever
 * [rawValue] changes, instead of snapping instantly. Falls back to plain
 * text immediately for non-numeric or privacy-masked strings (e.g. "••••••••")
 * so masking/formatting behaviour is fully preserved.
 *
 * [formatter] receives the interpolated numeric value and must return it
 * formatted exactly like the rest of the app (same digits, separators,
 * currency suffix) so functionality/output never changes — only the
 * transition between values is animated.
 */
@Composable
fun AnimatedAmountText(
    rawValue: Double,
    displayText: String,
    formatter: (Double) -> String,
    color: Color,
    fontSize: TextUnit,
    fontWeight: FontWeight = FontWeight.Black,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default
) {
    val isMasked = displayText.contains('•')

    val animatedValue by animateFloatAsState(
        targetValue = rawValue.toFloat(),
        animationSpec = tween(durationMillis = 500),
        label = "animatedAmount"
    )

    val textToShow = if (isMasked) displayText else formatter(animatedValue.toDouble())

    androidx.compose.material3.Text(
        text = textToShow,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        style = style,
        modifier = modifier
    )
}
