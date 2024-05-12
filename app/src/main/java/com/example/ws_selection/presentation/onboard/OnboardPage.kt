package com.example.ws_selection.presentation.onboard

import androidx.annotation.DrawableRes
import com.example.ws_selection.R

sealed class OnboardPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {

    data object Second: OnboardPage(
        R.drawable.ic_onboard2,
        "Начнем путешествие",
        "Умная, великолепная и модная коллекция Изучите сейчас"
    )
    data object Third: OnboardPage(
        R.drawable.ic_onboard3,
        "У Вас Есть Сила, Чтобы",
        "В вашей комнате много красивых и привлекательных растений"
    )
}