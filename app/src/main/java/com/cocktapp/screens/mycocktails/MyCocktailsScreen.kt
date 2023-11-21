package com.cocktapp.screens.mycocktails

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cocktapp.model.Cocktail
import com.cocktapp.model.Cocktails
import com.cocktapp.navigation.AvaliableScreens
import com.cocktapp.navigation.NavbarForScaffoldWithLogoutAndBackButton
import com.cocktapp.screens.searchcocktails.CocktailSearchViewModel
import com.cocktapp.screens.searchcocktails.ShowData
import com.cocktapp.wrappers.DataRequestWrapper

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCocktailsScreen(navController: NavController, myCocktailsViewModel: MyCocktailsViewModel = hiltViewModel()){

    Scaffold(
        topBar = { NavbarForScaffoldWithLogoutAndBackButton(navController = navController, "My cocktails") },
        contentColor = Color.Black


    ) {
        Surface(
            modifier = Modifier
                //.padding(15.dp)
                .fillMaxSize(),
            color = Color.White,

            ) {
            Column(
                //modifier = Modifier.padding(2.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                //Text(text = "My cocktails screen")

                ShowData(myCocktailsViewModel = myCocktailsViewModel, navController = navController)
            }

        }
    }
}
@Composable
fun ShowData(myCocktailsViewModel: MyCocktailsViewModel, navController: NavController) {
    val cocktailData = produceState<DataRequestWrapper<Cocktails,String,Exception>>(initialValue = DataRequestWrapper(state="loading")){
        value = myCocktailsViewModel.getCocktailsFirestore()
    }.value

    if(cocktailData.state=="loading"){
        Text(text = "My cocktails screen")
        CircularProgressIndicator()
    }
    else if(cocktailData.data!=null)
    {
        Log.d("DONE","LOADING DATA DONE")
        Column(
            //modifier = Modifier.padding(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "My cocktails screen")
            CocktailsList(cocktails = cocktailData.data ?: emptyList(), navController = navController)
        }

        /*cocktailData.data?.forEach { cocktail ->
            Text(text = cocktail.name)
            Text(text = cocktail.ingredients.toString())
            Text(text = cocktail.instructions)
            Text(text = "Number of Ingredients: ${cocktail.ingredients.size}")

        }*/
    }
    else{
        Text(text = "No cocktails found, data is null")
    }

}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CocktailsList(cocktails: List<Cocktail>, navController: NavController) {
    var number = 0
    LazyColumn {
        items(cocktails) { cocktail ->
            number++
            CocktailCard(cocktail = cocktail, number = number, navController = navController)
        }
    }
}
/*
@Composable
fun CocktailCard(cocktail: Cocktail) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "Nombre: ${cocktail.name}")
            Text(text = "Ingredients: ${cocktail.ingredients.joinToString(", ")}")
            Text(text = "Instructions: ${cocktail.instructions}")
            Text(text = "Number of Ingredients: ${cocktail.ingredients.size}")
        }
    }
}
*/

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
                navController.navigate(AvaliableScreens.CocktailDetailsScreen.name)
            }
            .padding(36.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "Nombre: ${cocktail.name}", color = Color.Black)
            Text(text = "Ingredients: ${cocktail.ingredients.joinToString(", ")}", color = Color.Black)
            Text(text = "Instructions: ${cocktail.instructions}", color = Color.Black)
            Text(text = "Number of Ingredients: ${cocktail.ingredients.size}", color = Color.Black)
        }
    }
}


