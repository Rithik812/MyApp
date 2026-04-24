package com.example.myapp.presentation.common.shimmer
import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun shimmerBrush(): Brush {

    val colors = listOf(
        Color(0xFFEAEAEA),
        Color(0xFFF5F5F5),
        Color(0xFFEAEAEA)
    )

    val transition = rememberInfiniteTransition()

    val translate by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing)
        ),
        label = ""
    )

    return Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = Offset(translate, translate)
    )
}