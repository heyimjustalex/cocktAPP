package com.cocktapp.screens.searchcocktails

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cocktapp.model.Cocktails
import com.cocktapp.navigation.NavbarForScaffoldWithLogoutAndBackButton
import com.cocktapp.screens.mycocktails.MyCocktailsViewModel
import com.cocktapp.wrappers.DataRequestWrapper

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailSearchScreen(navController: NavController, cocktailSearchViewModel: CocktailSearchViewModel = hiltViewModel()){
    Scaffold(
        topBar = { NavbarForScaffoldWithLogoutAndBackButton(navController = navController, "Search Cocktails") },
        contentColor = Color.Black


    ) {

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

                Text(text = "Search cocktails screen")

                ShowData(cocktailSearchViewModel = cocktailSearchViewModel)

            }

        }
    }



}
@Composable
fun ShowData(cocktailSearchViewModel: CocktailSearchViewModel){
    val cocktailData = produceState<DataRequestWrapper<Cocktails,String,Exception>>(initialValue = DataRequestWrapper(state="loading")){
        value = cocktailSearchViewModel.getCocktailsByIngredients("vodka,rum")
        //     value = myCocktailsViewModel.getCocktailsByName()
    }.value

    if(cocktailData.state=="loading"){
        CircularProgressIndicator()
    }
    else if(cocktailData.data!=null)
    {
        Log.d("DONE","LOADING DATA DONE")
        cocktailData.data?.forEach { cocktail ->
            Text(text = cocktail.name)
            Text(text = cocktail.ingredients.toString())
            Text(text = cocktail.instructions)
        }
    }
    else{
        Text(text = "No cocktails found, data is null")
    }
}
