@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.example.ws_selection.presentation.Swipe

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe
import de.charlex.compose.rememberRevealState
import kotlin.math.min
import kotlin.math.roundToInt


@Composable
fun SwipeScreen() {

    val state = rememberRevealState(
        directions = setOf(RevealDirection.StartToEnd)
    )

    val dismissState = rememberSwipeToDismissBoxState()

    Column(
        Modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        SwipeToDismissBox(
            state = dismissState,
            enableDismissFromStartToEnd = false,
            backgroundContent = {
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        ) {
            RevealSwipe(
                state = state,
                backgroundStartActionLabel = null,
                backgroundEndActionLabel = null,
                hiddenContentStart = {
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Blue)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }
                ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.Gray))
            }
        }
        Spacer(modifier = Modifier.height(50.dp))

        Box(
            Modifier
                .fillMaxWidth()
        ) {

            Box(
                Modifier
                    .background(Color.Red)
                    .height(60.dp)
                    .align(Alignment.CenterEnd),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }

            }

            val density = LocalDensity.current

            val state = remember {
                AnchoredDraggableState(
                    initialValue = DragAnchors.Start,
                    positionalThreshold = { distance: Float -> distance * 0.5f},
                    velocityThreshold = { with(density){ (100.dp).toPx() } },
                    animationSpec = tween()
                ).apply {
                    updateAnchors(
                        DraggableAnchors {
                            DragAnchors.Start at 0f
                            DragAnchors.End at 400f
                        }

                    )
                }
            }

            Box(
                Modifier
                    .offset {
                        IntOffset(
                            x = state
                                .requireOffset()
                                .roundToInt(), y = 0
                        )
                    }
                    .width(60.dp)
                    .height(60.dp)
                    .background(Color.Blue)
                    .anchoredDraggable(
                        state = state,
                        orientation = Orientation.Horizontal
                    )
//                    .draggable(
//                        orientation = Orientation.Horizontal,
//                        state = rememberDraggableState { delta ->
//                            offsetValue += delta
//                        }
//                    )
//                    .pointerInput(Unit) {
////                    detectTapGestures {
////                        Log.i("client", "tap: ${it.x} ${it.y}")
////                    }
//                        detectHorizontalDragGestures(
//                            onDragEnd = {
//                                Log.i("client", "end")
//                                startPosition = 0f
//                            },
//                            onDragStart = {
//                                Log.i("client", "start")
//                                startPosition = it.x
//                            },
//                            onDragCancel = {
//                                Log.i("client", "cancal")
//                            }
//                        ) { change, dragAmount ->
////                        Log.i("client", "change: ${change.position.x}, dragAmount: $dragAmount")
////                            offsetValue = change.position.x - startPosition
//                        }
//
//                    }

            ) {

            }

        }

        Spacer(modifier = Modifier.height(32.dp))
        CustomReveal()
    }

}


@Composable
fun CustomReveal() {

    var offsetValue by remember {
        mutableFloatStateOf(0f)
    }

    val density = LocalDensity.current

    var maxSize by remember {
        mutableStateOf(0.dp)
    }

    var itemSize by remember {
        mutableStateOf(0.dp)
    }

    var minSize by remember {
        mutableStateOf(0.dp)
    }

    Box(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .height(104.dp)
            .onGloballyPositioned { coordinates ->
                maxSize = with(density) { coordinates.size.width.toDp() }
                if (itemSize == 0.dp) {
                    itemSize = maxSize
                }
            }
    ) {
        Box(
            Modifier
                .clip(RoundedCornerShape(12.dp))
                .padding(start = 8.dp)
                .background(Color.Red, RoundedCornerShape(12.dp))
                .fillMaxHeight()
                .width(58.dp)
                .onGloballyPositioned { coordinates ->
                    minSize = with(density) { coordinates.size.width.toDp() } + 8.dp
                }
                .align(Alignment.CenterEnd),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.Black
                )
            }

        }

        Box(
            Modifier
                .clip(RoundedCornerShape(12.dp))
                .padding(end = 8.dp)
                .background(Color.Blue, RoundedCornerShape(12.dp))
                .fillMaxHeight()
                .width(58.dp)
                .align(Alignment.CenterStart),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = null,
                    tint = Color.Black
                )
            }

        }

        Box(
            Modifier
                .clip(RoundedCornerShape(12.dp))
                .width(
                    animateDpAsState(
                        targetValue = itemSize,
                        animationSpec = tween(500),
                        label = "Width Animation"
                    ).value
                )
                .align(if (offsetValue > 0f) Alignment.CenterEnd else Alignment.CenterStart)
                .fillMaxHeight()
                .background(Color.Gray)
                .pointerInput(
                    Unit
                ) {
                    detectHorizontalDragGestures(
                        onDragStart = {
                        },
                        onDragEnd = {
                            itemSize = if (minSize - (maxSize - itemSize) >= minSize / 2) maxSize
                            else maxSize - minSize
                        },
                        onDragCancel = {
                            itemSize = if (minSize - (maxSize - itemSize) >= minSize / 2) maxSize
                            else maxSize - minSize
                        }
                    ) { _, dragAmount ->
                        offsetValue += dragAmount
                        val redactSize = with(density) { offsetValue.toDp() }

                        Log.i("client", "redactSize: $redactSize")
                        if (offsetValue < 0f) {
                            if (redactSize + maxSize >= maxSize - minSize) {
                                itemSize = redactSize + maxSize
                            } else {
                                offsetValue -= dragAmount
                            }
                        } else {
                            if (maxSize - redactSize >= maxSize - minSize) {
                                itemSize = maxSize - redactSize
                            } else {
                                offsetValue -= dragAmount
                            }
                        }

                        Log.i("client", "offset: $offsetValue")
                    }
                }
        ) {

        }
    }
}


enum class DragAnchors{
    Start,
    End
}