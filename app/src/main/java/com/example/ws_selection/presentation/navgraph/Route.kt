package com.example.ws_selection.presentation.navgraph

sealed class Route(val route: String) {

    data object NavScreen: Route("NavScreen")


    data object Onboard: Route("Onboard")
    data object Drawer: Route("Drawer")
    data object Slider: Route("Slider")
    data object Swipe: Route("Swipe")
    data object Notifications: Route("Notifications")
    data object Generators: Route("Generators")

}