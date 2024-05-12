package com.example.ws_selection.presentation.Notifications

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NotificationScreen(

) {
    val notifications = remember {
        mutableStateListOf(
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),NotificationItem(
                "0",
                false
            ),NotificationItem(
                "0",
                false
            ),NotificationItem(
                "0",
                false
            ),NotificationItem(
                "0",
                false
            ),NotificationItem(
                "0",
                false
            ),NotificationItem(
                "0",
                false
            ),NotificationItem(
                "0",
                false
            ),NotificationItem(
                "0",
                false
            ),NotificationItem(
                "0",
                false
            ),NotificationItem(
                "0",
                false
            ),NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),
            NotificationItem(
                "0",
                false
            ),NotificationItem(
                "0",
                false
            ),NotificationItem(
                "0",
                false
            ),NotificationItem(
                "0",
                false
            ),



            )
    }

    val listState = rememberLazyListState()
    var lastVisibleItem by remember {
        mutableStateOf(listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index)
    }
    var firstVisibleItem by remember {
        mutableStateOf(listState.layoutInfo.visibleItemsInfo.firstOrNull()?.index)
    }

    if (listState.lastVisibleElement() != lastVisibleItem){
        lastVisibleItem = listState.lastVisibleElement()
    }

    if (listState.firstVisibleElement() != firstVisibleItem){
        firstVisibleItem = listState.firstVisibleElement()
    }

    val activity = LocalView.current.context as? Activity

    if (activity!= null){
        activity.window?.attributes = activity.window.attributes.apply {
            screenBrightness = 1f
        }
    }


    Column {
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(notifications){ index, item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "index $index",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .weight(1f)
                            .height(100.dp)
                            .background(Color.Gray.copy(0.5f))
                    )
                    if (item.isViewed){
                        Text(
                            text = "is viewed",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 24.sp,
                            )
                        )
                    }

                    if (!item.isViewed){
                        LaunchedEffect(key1 = index in (firstVisibleItem ?: 0)..(lastVisibleItem ?: 0)) {
                            if (index in (firstVisibleItem ?: 0)..(lastVisibleItem ?: 0)){
                                delay(1000)
                                notifications[index] = notifications[index].copy(isViewed = true)
                                Log.i("client", "$index is complete")
                            }
                        }
                    }

                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "First visible index: $firstVisibleItem", Modifier.padding(horizontal = 24.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Last visible index: $lastVisibleItem", Modifier.padding(horizontal = 24.dp))

    }
   
}

fun LazyListState.lastVisibleElement() = layoutInfo.visibleItemsInfo.lastOrNull()?.index
fun LazyListState.firstVisibleElement() = layoutInfo.visibleItemsInfo.firstOrNull()?.index