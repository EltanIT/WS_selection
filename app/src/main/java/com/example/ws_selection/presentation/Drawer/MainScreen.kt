package com.example.ws_selection.presentation.Drawer

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.ws_selection.presentation.Drawer.components.CustomDrawer
import com.example.ws_selection.utils.coloredShadow
import kotlin.math.roundToInt


val navigationList = listOf(
    NavigationItem(
        "Home",
        Icons.Default.Home
    ),
    NavigationItem(
        "Profile",
        Icons.Default.Person
    ),
    NavigationItem(
        "Setting",
        Icons.Default.Settings
    ),
)

@Composable
fun MainScreen() {

    var drawerState by remember{ mutableStateOf(CustomDrawerState.Closed)}
    var selectedNavigationItem by remember{ mutableStateOf(navigationList[0])}

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density

    val screenWidth = remember {
        derivedStateOf { (configuration.screenWidthDp * density).roundToInt() }
    }

    val offsetValue by remember {
        derivedStateOf { (screenWidth.value / 3.7).dp }
    }
    
    val animatedOffset by animateDpAsState(
        targetValue = if (drawerState.isOpened()) offsetValue else 0.dp,
        label = "Animated Offset"
    )

    val animatedScale by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) 0.75f else 1f,
        label = "Animated Scale"
    )

    val animatedRotate by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) -4f else 0f,
        label = "Animated Rotate"
    )

    BackHandler(enabled = drawerState.isOpened()) {
        drawerState = CustomDrawerState.Closed
    }

    Box(
        Modifier
            .background(MaterialTheme.colorScheme.surface)
            .background(MaterialTheme.colorScheme.onSurface.copy(0.5f))
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
    ) {
        CustomDrawer(
            selectedItem = selectedNavigationItem,
            navigationList = navigationList,
            onNavigationItemClick = { selectedNavigationItem = it }
        ) {
            drawerState = CustomDrawerState.Closed
        }

        MainContent(
            modifier = Modifier
                .offset(x = animatedOffset)
                .scale(scale = animatedScale)
                .rotate(degrees = animatedRotate)
                .clip(
                    if (drawerState.isOpened()) RoundedCornerShape(25.dp) else RoundedCornerShape(
                        0.dp
                    )
                )
                .coloredShadow(
                    color = Color.Black,
                    alpha = 0.1f,
                    shadowRadius = 25.dp
                ),
            drawerState = drawerState
        ) {
            drawerState = it
        }
    }
}