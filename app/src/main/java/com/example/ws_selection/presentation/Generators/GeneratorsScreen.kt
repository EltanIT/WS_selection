package com.example.ws_selection.presentation.Generators

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ws_selection.presentation.common.CustomPrimaryButton


@Composable
fun GeneratorsScreen() {

    var phraseValue by remember {
        mutableStateOf("")
    }

    var generatedPassword by remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        TextField(value = phraseValue, onValueChange = {phraseValue = it})
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = generatedPassword)
        Spacer(modifier = Modifier.height(24.dp))
        CustomPrimaryButton(
            title = "generate Pass",
            onClickListener = { generatedPassword = generatePasswordFromPhrase(phraseValue) }
        )

    }
}


fun generatePasswordFromPhrase(phrase: String): String {
    val password = StringBuilder()

    var isUpperCase = false

    for (char in phrase) {
        var char = char
        if (char.isLetter()){
            char = char.toLowerCase()
        }

        when (char) {
            'а' -> password.append('A')
            'е' -> password.append('E')
            'о' -> password.append('0')
            'с' -> password.append('c')
            'р' -> password.append('p')
            'к' -> password.append('k')
            'м' -> password.append('M')
            'г' -> password.append('g')
            'н' -> password.append('№')
            'п' -> password.append('p')
            'в' -> password.append('v')
            'д' -> password.append('9')
            'ч' -> password.append('4')
            'я' -> password.append('I')
            '№' -> password.append('#')
            'ю' -> password.append('u')
            ' ' -> password.append(listOf(" ", "_").random())
            else -> password.append(char)
        }
        if (password.last().isLowerCase() && !isUpperCase){
            val char = password.last()
            password.dropLast(1)
            password.append(char.toUpperCase())
            isUpperCase = true
        }
    }

    return password.toString()
}
