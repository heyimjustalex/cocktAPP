package com.cocktapp.screens.cocktails

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cocktapp.components.CocktailsList
import com.cocktapp.model.Cocktails
import com.cocktapp.navigation.AvaliableScreens
import com.cocktapp.wrappers.DataRequestWrapper



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowData(
    loadCocktails: suspend () -> DataRequestWrapper<Cocktails, String, Exception>,
    navController: NavController
) {
    val cocktailData = produceState<DataRequestWrapper<Cocktails, String, Exception>>(initialValue = DataRequestWrapper(state="loading")){
        value = loadCocktails()
    }.value

    if (cocktailData.state == "loading") {
        Text(text = "Cocktails screen")
        CircularProgressIndicator()
    } else if (cocktailData.data != null && !cocktailData.data!!.isEmpty()) {
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
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "No cocktails found, data is null",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp) // Ajusta el padding según tus preferencias
                    .wrapContentSize(Alignment.Center)
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

    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowDataSearch(
    loadCocktails: DataRequestWrapper<Cocktails, String, Exception>,
    navController: NavController
) {
    when (loadCocktails.state) {
        "loading" -> CircularProgressIndicator()
        else -> {
            if (loadCocktails.data != null && loadCocktails.data!!.isNotEmpty()) {
                Log.d("DONE","LOADING DATA DONE")
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CocktailsList(
                        cocktails = loadCocktails.data ?: emptyList(),
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
                Text("No cocktails found")
            }
        }
    }
}