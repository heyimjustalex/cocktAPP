package com.cocktapp.components

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cocktapp.model.Cocktail
import com.cocktapp.navigation.AvaliableScreens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CocktailsList(cocktails: List<Cocktail>, navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        itemsIndexed(cocktails) { index, cocktail ->
            CocktailCard(cocktail = cocktail, number = index + 1, navController = navController)
        }
    }
}

@Composable
fun CocktailCard(cocktail: Cocktail, number: Int, navController: NavController) {

    val isEven = number % 2 == 0
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (isEven){
                    Color.Gray
                } else {
                    Color.LightGray
                }
            )
            .clickable{
                navController.navigate("${AvaliableScreens.CocktailDetailsScreen.name}/?cocktail=${Uri.encode(cocktail.toString())}")
            }
            .padding(36.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "${cocktail.name.uppercase()} ",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )
            Text(text = "Number of Ingredients: ${cocktail.ingredients.size}", color = Color.Black)
            Text(text = "This Cocktail is from: ${cocktail.fromWhere}", color = Color.Black)
        }
    }
}
