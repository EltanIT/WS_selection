package com.example.ws_selection.presentation.common

import android.content.DialogInterface.OnClickListener
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
fun CustomPrimaryButton(
    title: String,
    onClickListener: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    
    Button(
        onClick = onClickListener,
        shape = RoundedCornerShape(13.dp),
        enabled = enabled,
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        )
    ) {
        Text(
            text = title,
            style = TextStyle(color = Color(0xff2B2B2B))
        )
    }
}