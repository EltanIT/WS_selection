package com.example.ws_selection.presentation.Slider

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlin.math.min


@Composable
fun SliderScreen(

) {

    var progress by remember {
        mutableFloatStateOf(0f)
    }

    Column(
        Modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isDragging by remember { mutableStateOf(false) }

        Canvas(modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    progress = (progress + dragAmount.x / size.width).coerceIn(0f, 1f)

                    change.consumeAllChanges()
                }
            }
        ) {
            // Рисуем округлую тонкую горизонтальную линию
            val curveRadius = 10f // Радиус закругления
            val lineWidth = 5f // Толщина линии

            drawRoundRect(
                color = Color.Black,
                topLeft = Offset(0f, (size.height / 2) - (lineWidth / 2)),
                size = Size(size.width, lineWidth),
                cornerRadius = CornerRadius(curveRadius, curveRadius)
            )

            // Рисуем ползунок
            val thumbRadius = 12f
            val thumbX = size.width * progress

            drawCircle(
                color = Color.Red,
                radius = thumbRadius,
                center = Offset(thumbX, size.height / 2)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))


        var sliderPosition by remember { mutableFloatStateOf(0f) }
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
        ) {

            val path = Path().apply {
                moveTo(0f, center.y)
                cubicTo(
                    x1 = size.width/4, y1 = size.height,
                    x2 = size.width - (size.width/4), y2 = size.height,
                    x3 = size.width, y3 = center.y
                )
            }

            drawPath(
                path = path,
                color = Color.Black,
                style = Stroke(width = 5f)
            )
        }
    }

}


