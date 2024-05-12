package com.example.ws_selection.presentation.onboard.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CustomPagerIndicator(
    selectedPage: Int,
    count: Int,
    modifier: Modifier
) {



    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(count){ index ->
            val isSelected = selectedPage == index

            val width by animateDpAsState(
                targetValue = if (isSelected) 42.dp else 28.dp,
                label = "Selected Indicator"
            )

            Box(
                modifier = Modifier
                    .height(5.dp)
                    .width(width)
                    .clip(CircleShape)
                    .background(if (isSelected) Color.White else Color(0x802B6B8B), CircleShape)
            ) {

            }
            Spacer(modifier = Modifier.width(10.dp))
        }

    }
}