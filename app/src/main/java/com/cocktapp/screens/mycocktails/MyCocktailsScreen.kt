package com.cocktapp.screens.mycocktails

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cocktapp.components.CocktailsList
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
                .padding(top = 60.dp)
                .fillMaxSize(),
            color = Color.White,

            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
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

    if (cocktailData.state == "loading") {
        Text(text = "My cocktails screen")
        CircularProgressIndicator()
    } else if (cocktailData.data != null) {
        Log.d("DONE","LOADING DATA DONE")
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CocktailsList(
                cocktails = cocktailData.data ?: emptyList(),
                navController = navController
            )

            Button(
                onClick = {
                    navController.navigate(AvaliableScreens.CocktailAddScreen.name)
                },
                modifier = Modifier
                    .padding(26.dp)
                    .align(Alignment.BottomEnd)
                    .height(56.dp)
                //.align(Alignment.BottomCenter) To align Horizontally center
            ) {
                Text(text = "Add")
            }
        }
    } else {
        Text(
            text = "No cocktails found, data is null"
        )
    }
}


