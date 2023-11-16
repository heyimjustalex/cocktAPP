package com.cocktapp.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.DrawableCompat.setTint
import androidx.navigation.NavController
import com.cocktapp.R
import com.cocktapp.ui.theme.CocktailBlackColor
import com.cocktapp.ui.theme.CocktailOrangeColor
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.app
import com.google.firebase.auth.FirebaseAuth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavbarForScaffoldWithLogout(navController: NavController, text:String){

    TopAppBar(
        title = { Text(text = text) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Black,
            titleContentColor = CocktailOrangeColor
        ),
        actions = {
            logoutIcon(navController = navController)

        }
    )

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavbarForScaffoldWithLogoutAndBackButton(navController: NavController, text: String){

    TopAppBar(
        title = { Text(text = text) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = CocktailBlackColor,
            titleContentColor = CocktailOrangeColor
        ),
        navigationIcon = {
            navIcon(navController)
        },
        actions = {
           logoutIcon(navController = navController)

        }
    )

}

@Composable
fun navIcon(navController: NavController){
    Row(horizontalArrangement = Arrangement.Start) {
        IconButton(onClick = { navController.popBackStack()}) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "Back arrow",
                tint = CocktailOrangeColor
            )
        }
    }
}

@Composable
fun logoutIcon(navController: NavController){
    Row(horizontalArrangement = Arrangement.Start) {
        IconButton(onClick = {
            FirebaseAuth.getInstance().signOut().run {
                navController.navigate(AvaliableScreens.LoginScreen.name)
            }

        }) {
            Image(
                modifier = Modifier.size(18.dp),
                contentScale = ContentScale.Fit,
                painter = painterResource(R.drawable.logout),
                contentDescription = "Logout",
                colorFilter = ColorFilter.tint(CocktailOrangeColor)
            )
        }
    }
}