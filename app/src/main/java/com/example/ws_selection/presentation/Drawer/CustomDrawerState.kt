package com.example.ws_selection.presentation.Drawer

enum class CustomDrawerState {
    Opened,
    Closed;

    fun isOpened(): Boolean = this.name == "Opened"
}

fun CustomDrawerState.opposite(): CustomDrawerState =
    if (this == CustomDrawerState.Opened) CustomDrawerState.Closed
    else CustomDrawerState.Opened