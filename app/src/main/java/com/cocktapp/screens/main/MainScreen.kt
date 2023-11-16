package com.cocktapp.screens.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cocktapp.navigation.AvaliableScreens
import com.cocktapp.ui.theme.CocktailFonts

@Composable
fun MainScreen(navController: NavController)
{
    Surface(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize()      ,
        color = Color.White,

    ) {
        Column(
            modifier = Modifier.padding(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {



         Button(onClick = { navController.navigate(AvaliableScreens.MyCocktailsScreen.name)}) {
             Text(text = "My cocktails")

         }
            Button(onClick = { navController.navigate(AvaliableScreens.CocktailSearchScreen.name)}) {
                Text(text = "Search cockatils")

            }
            Button(onClick = {
                // FIRST WAY OF DOING IT (PREFERRED)
                // Here you should start Find a bar activity that will call google maps application

                // SECOND WAY OF DOING IT
                // If there is no way of calling app, you can go for creating new screen (Avaliable screens for routing, and
                // then like in "screens" package every other screen with viewModel
                // and then calling navcontroller here to redirect to viewModel

            }) {
                Text(text = "Find a bar")

            }
        }

    }
}
