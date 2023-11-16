package com.cocktapp.screens.searchcocktails

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
import com.cocktapp.model.Cocktails
import com.cocktapp.screens.mycocktails.MyCocktailsViewModel
import com.cocktapp.wrappers.DataRequestWrapper

@Composable
fun CocktailSearchScreen(navController: NavController, cocktailSearchViewModel: CocktailSearchViewModel = hiltViewModel()){
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
