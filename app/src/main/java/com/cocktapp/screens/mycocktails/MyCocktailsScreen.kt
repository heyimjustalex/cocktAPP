package com.cocktapp.screens.mycocktails

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import com.cocktapp.components.CocktailsList
import com.cocktapp.model.Cocktails
import com.cocktapp.navigation.AvaliableScreens
import com.cocktapp.navigation.NavbarForScaffoldWithLogoutAndBackButton
import com.cocktapp.wrappers.DataRequestWrapper

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCocktailsScreen(navController: NavController, cocktailsViewModel: MyCocktailsViewModel = hiltViewModel()){

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
                ShowData(
                    loadCocktails = { cocktailsViewModel.getCocktailsFirestore() },
                    navController = navController
                )
            }

        }
    }
}



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
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    onClick = { navController.navigate(AvaliableScreens.CocktailAddScreen.name) },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(18.dp)
                        .size(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF161616),
                        contentColor = Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add, // Use of the material icon
                        contentDescription = "Add"
                    )
                }
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
                    .padding(16.dp) // Adjust the padding according to your preferences
                    .wrapContentSize(Alignment.Center)
            )
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    onClick = { navController.navigate(AvaliableScreens.CocktailAddScreen.name) },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(18.dp)
                        .size(70.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF161616),
                        contentColor = Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add, // Use of the material icon
                        contentDescription = "Add"
                    )
                }
            }
        }

    }
}
