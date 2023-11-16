package com.cocktapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cocktapp.navigation.AvaliableScreens
import com.cocktapp.ui.theme.CocktailBlackColor
import com.cocktapp.ui.theme.CocktailDarkGrayColor
import com.cocktapp.ui.theme.CocktailWhiteColor


@Composable
fun BigMainScreenButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = CocktailDarkGrayColor,
            contentColor = CocktailWhiteColor,
            disabledContentColor = CocktailWhiteColor,
            disabledContainerColor = CocktailDarkGrayColor
        ),
        shape= RectangleShape,
        modifier = Modifier
            .height(180.dp)
            .padding(10.dp)
            .fillMaxWidth()

    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
