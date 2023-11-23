package com.cocktapp.screens.cocktailDetails

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.cocktapp.model.Cocktail
import com.cocktapp.navigation.NavbarForScaffoldWithLogoutAndBackButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailDetailsScreen(navController: NavController, cocktailString: String) {

    // Extract Ingredients form cocktailString
    val ingredientsRegex = Regex("""ingredients=\[([^\]]+)\]""")
    val ingredientsMatch = ingredientsRegex.find(cocktailString)
    val ingredientsList = ingredientsMatch?.groupValues?.get(1)?.split(", ") ?: emptyList()

    // Extract Instructions form cocktailString
    val instructionsRegex = Regex("""instructions=([^,]+),""")
    val instructionsMatch = instructionsRegex.find(cocktailString)
    val instructions = instructionsMatch?.groupValues?.get(1) ?: ""

    // Extract Name form cocktailString
    val nameRegex = Regex("""name=([^,]+)(?:\))""")
    val nameMatch = nameRegex.find(cocktailString)
    val name = nameMatch?.groupValues?.get(1)?.trim() ?: ""

    Scaffold(
        topBar = { NavbarForScaffoldWithLogoutAndBackButton(navController = navController, "Cocktail Details") },
        contentColor = Color.Black


    ) {
        Surface(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize(),
            color = Color.White,

            ) {
            Column(
                modifier = Modifier.padding(2.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(text = "Cocktail Details screen")
                Text(text = "NAME -> ${name}", color = Color.Black)
                Text(text = "INSTRUCTIONS -> ${instructions}", color = Color.Black)
                Text(text = "INGREDIENTS -> ${ingredientsList}", color = Color.Black)

            }

        }
    }
}