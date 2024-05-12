package com.example.ws_selection.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ws_selection.presentation.MainActivityViewModel
import com.example.ws_selection.presentation.navgraph.MainNavHost
import com.example.ws_selection.presentation.navgraph.Route
import com.example.ws_selection.presentation.ui.theme.WS_selectionTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.isOnline(this)
        setContent {
            WS_selectionTheme {
                val isConnectionTrue = viewModel.isConnectionTrue.collectAsState()
                val navController = rememberNavController()
                MainNavHost(navController = navController, startDestination = Route.NavScreen.route)

                if (!isConnectionTrue.value){
                    AlertDialog(
                        onDismissRequest = viewModel::closeAlertDialog,
                        confirmButton = {
                            Button(onClick = viewModel::closeAlertDialog) {
                                Text(text = "Ок")
                            }
                        },
                        title = {
                            Text(text = "Нет подключения к интернету")
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun NavScreen(navController: NavController) {
    Column(
        Modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = { navController.navigate(Route.Onboard.route) }) {
            Text(text = "Onboard", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.navigate(Route.Drawer.route) }) {
            Text(text = "Drawer", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.navigate(Route.Slider.route) }) {
            Text(text = "Slider", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.navigate(Route.Notifications.route) }) {
            Text(text = "Notifications", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.navigate(Route.Swipe.route) }) {
            Text(text = "Swipe", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.navigate(Route.Generators.route) }) {
            Text(text = "Generators", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))
    }

}
