package com.example.ws_selection.presentation.Drawer.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ws_selection.presentation.Drawer.NavigationItem


@Composable
fun CustomDrawer(
    selectedItem: NavigationItem,
    navigationList: List<NavigationItem>,
    onNavigationItemClick: (NavigationItem) -> Unit,
    onCloseClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.6f)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
        ){
            IconButton(onClick = onCloseClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Arrow Icon",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))


        navigationList.onEach { item ->
            NavigationItemView(
                item = item,
                isSelected = selectedItem == item
            ) {
                onNavigationItemClick(item)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        repeat(1){
            NavigationItemView(
                item = navigationList[0],
                isSelected = false
            ) {
                onNavigationItemClick(navigationList[0])
            }
        }
        Spacer(modifier = Modifier.height(24.dp))


    }
}