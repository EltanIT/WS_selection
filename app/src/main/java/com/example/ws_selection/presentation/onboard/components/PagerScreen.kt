package com.example.ws_selection.presentation.onboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ws_selection.R
import com.example.ws_selection.presentation.onboard.OnboardPage


@Composable
fun PagerScreen(
    page: OnboardPage,
    pageNumber: Int
) {

    Column(
        Modifier
            .padding(top = 65.dp, start = 27.dp, end = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier.fillMaxWidth()
        ) {
            Image(painter = painterResource(
                id = if (pageNumber == 1) R.drawable.ic_highlight_onboard2 else R.drawable.ic_misc_05),
                contentDescription = null,
            )
            if (pageNumber == 1){
                Image(painter = painterResource(
                    id = R.drawable.ic_misc_04),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }

            Image(
                painter = painterResource(id = page.image),
                contentDescription = "Image",
                modifier = Modifier
                    .height(339.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth

            )
        }
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = page.title,
            style = TextStyle(
                fontWeight = FontWeight(700),
                fontSize = 34.sp,
                lineHeight = 44.2.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = page.description,
            style = TextStyle(
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = Color(0xffD8D8D8),
                textAlign = TextAlign.Center
            )
        )
    }
   
}