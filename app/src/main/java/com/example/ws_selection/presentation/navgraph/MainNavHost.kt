package com.example.ws_selection.presentation.navgraph

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ws_selection.presentation.NavScreen
import com.example.ws_selection.presentation.Drawer.MainScreen
import com.example.ws_selection.presentation.Generators.GeneratorsScreen
import com.example.ws_selection.presentation.Notifications.NotificationScreen
import com.example.ws_selection.presentation.Slider.SliderScreen
import com.example.ws_selection.presentation.Swipe.SwipeScreen
import com.example.ws_selection.presentation.onboard.OnboardScreen


@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: String
) {

    NavHost(navController, startDestination){
        composable(Route.Onboard.route){
            OnboardScreen()
        }

        composable(Route.Drawer.route){
            MainScreen()
        }

        composable(Route.Slider.route){
            SliderScreen()
        }

        composable(Route.NavScreen.route){
            NavScreen(navController)
        }

        composable(Route.Notifications.route){
            val activity = LocalView.current.context as? Activity
            BackHandler(true) {
                navController.popBackStack()
                if (activity!= null){
                    activity.window?.attributes = activity.window.attributes.apply {
                        screenBrightness = -1f
                    }
                }
            }
            NotificationScreen()
        }

        composable(Route.Swipe.route){
            SwipeScreen()
        }

        composable(Route.Generators.route){
            GeneratorsScreen()
        }
    }
}