package com.cocktapp.screens


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.cocktapp.R
import com.cocktapp.navigation.AvaliableScreens
import com.cocktapp.ui.theme.CocktailFonts
import com.cocktapp.ui.theme.CocktailType

@Composable
fun EntryLoadingScreen(navController: NavController) {

    val assetScale = remember {
       Animatable(0.85f)
    }

    LaunchedEffect(key1 = true, block = {
        assetScale.animateTo(targetValue = 0.95f,  animationSpec = repeatable(3,
            animation = tween(300),
            repeatMode = RepeatMode.Reverse
        ) )
        navController.navigate(AvaliableScreens.MainScreen.name)
    })


    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(340.dp)
            .scale(assetScale.value),

        shape = CircleShape,

        color = Color.White,
        border = BorderStroke(width = 3.dp, color = Color.Black)
    ) {
        Column(
            modifier = Modifier.padding(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Fit,
                painter = painterResource(R.drawable.cocktail),
                contentDescription = "Cocktail icon"
            )
            Text(
                text = AnnotatedString("Let's have a drink!", ParagraphStyle(textAlign = TextAlign.Center)),
                fontFamily = CocktailFonts.dancingScriptFamily,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Medium,
                fontSize = 27.sp

            )

        }

    }
}
