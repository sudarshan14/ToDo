package com.amlavati.todo.ui.screens.list

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.core.graphics.withSave
import com.amlavati.todo.ui.theme.LARGE_PADDING
import com.amlavati.todo.ui.theme.PRIORITY_INDICATOR_SIZE
import com.amlavati.todo.ui.theme.TASK_ITEM_ELEVATION

@Composable
fun ShowLoader(innerPadding: PaddingValues) {
    ShimmerEffect(modifier = Modifier.fillMaxSize())
}

@Composable
fun ShimmerEffect(modifier: Modifier) {
    val transition = rememberInfiniteTransition(label = "")
    val animatedValue by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1000, easing = LinearEasing),
            RepeatMode.Restart
        ), label = ""
    )

    Column(modifier) {
        repeat(5) { index ->
            ShimmerItem(modifier = Modifier.fillMaxWidth(), animatedValue = animatedValue)
        }
    }
}

@Composable
fun ShimmerItem(modifier: Modifier, animatedValue: Float) {
    Box(modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas { canvas ->
                val paint = androidx.compose.ui.graphics.Paint().asFrameworkPaint()
                val width = size.width
                val height = size.height
                val colors = intArrayOf(
                    Color.Transparent.toArgb(),
                    Color.White.toArgb(),
                    Color.Transparent.toArgb()
                )
                val positions = floatArrayOf(
                    0f,
                    animatedValue,
                    1f
                )
                val shader = android.graphics.LinearGradient(
                    0f, 0f, width, height,
                    colors, positions,
                    android.graphics.Shader.TileMode.CLAMP
                )
                paint.shader = shader

                canvas.nativeCanvas.withSave {
                    val rect = android.graphics.RectF(0f, 0f, width, height)
                    drawRoundRect(rect, 8f, 8f, paint)
                }
            }
        }
    }
}

@Composable
fun ShimmerListItem(
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    if (isLoading) {

        Column(
            modifier = Modifier
                .padding(LARGE_PADDING)
                .fillMaxWidth()
                .height(60.dp)

        ) {
            Row {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(8f)
                        .height(25.dp)
                        .shimmerEffect()
                )

                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .weight(1f)
                        .shimmerEffect()
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .shimmerEffect()
            )
        }


    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ), label = ""
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}