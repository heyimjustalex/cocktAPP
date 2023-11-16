package com.cocktapp.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import com.cocktapp.components.BigMainScreenButton
import com.cocktapp.navigation.AvaliableScreens
import com.cocktapp.navigation.NavbarForScaffoldWithLogout
import com.cocktapp.navigation.NavbarForScaffoldWithLogoutAndBackButton
import com.cocktapp.ui.theme.CocktailFonts

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController)
{
    Scaffold(
        topBar = { NavbarForScaffoldWithLogout(navController = navController, "Home") },
        contentColor = Color.Black


    ) {

        Surface(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize(),
            color = Color.White,

            ) {
            Column(
             //   modifier = Modifier.padding(2.dp),
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {

                BigMainScreenButton(text = "My cocktails") {
                    navController.navigate(AvaliableScreens.MyCocktailsScreen.name)
                }

                BigMainScreenButton(text = "Search cocktails") {
                    navController.navigate(AvaliableScreens.CocktailSearchScreen.name)
                }

                BigMainScreenButton(text = "Find a bar") {
                    // FIRST WAY OF DOING IT (PREFERRED)
                    // Here you should start Find a bar activity that will call google maps application

                    // SECOND WAY OF DOING IT
                    // If there is no way of calling app, you can go for creating new screen (Avaliable screens for routing, and
                    // then like in "screens" package every other screen with viewModel
                    // and then calling navcontroller here to redirect to viewModel
                }

            }

        }
    }
}
