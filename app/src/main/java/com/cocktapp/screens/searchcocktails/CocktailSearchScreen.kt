package com.cocktapp.screens.searchcocktails

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cocktapp.components.CocktailsList
import com.cocktapp.model.Cocktail
import com.cocktapp.model.Cocktails
import com.cocktapp.navigation.AvaliableScreens
import com.cocktapp.navigation.NavbarForScaffoldWithLogoutAndBackButton
import com.cocktapp.wrappers.DataRequestWrapper
import kotlinx.coroutines.async

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailSearchScreen(navController: NavController, cocktailSearchViewModel: CocktailSearchViewModel = hiltViewModel()) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val searchQuery = textState.value.text.trim()

    Scaffold(
        topBar = { NavbarForScaffoldWithLogoutAndBackButton(navController = navController, "Search Cocktails") },
        contentColor = Color.Black
    ) {
        Surface(
            modifier = Modifier
                .padding(15.dp, top = 80.dp)
                .fillMaxSize(),
            color = Color.White,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(
                    value = textState.value,
                    onValueChange = { textState.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    label = { Text("Search") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
                )
                ShowDataCombined(name = searchQuery, cocktailSearchViewModel = cocktailSearchViewModel, navController)
            }
        }
    }
}

@Composable
fun ShowDataCombined(name: String, cocktailSearchViewModel: CocktailSearchViewModel, navController: NavController) {
    var cocktailData by remember { mutableStateOf(DataRequestWrapper<Cocktails, String, Exception>(state = "loading")) }

    LaunchedEffect(name) {
        var name1 = name
        var name2 = name
        if (name.isEmpty()) {
            name1 = ""
            name2 = "a"
        }
        val firestoreCocktailsByName = async {
            cocktailSearchViewModel.getCocktailsFirestoreByName(name1)
        }
        val firestoreCocktailsByNameAll = async {
            cocktailSearchViewModel.getCocktailsFirestoreByNameAll(name1)
        }
        val firestoreCocktailsUser = async {
            cocktailSearchViewModel.getCocktailsFirestoreUser()
        }
        val firestoreCocktailsAll = async {
            cocktailSearchViewModel.getCocktailsFirestoreAll();
        }
        val localCocktails = async { cocktailSearchViewModel.getCocktailsByName(name2) }

        val firestoreCocktailsUserData = firestoreCocktailsUser.await();
        val firestoreCocktailsAllData = firestoreCocktailsAll.await();
        val firestoreCocktailsByNameData = firestoreCocktailsByName.await();
        val firestoreCocktailsByNameAllData = firestoreCocktailsByNameAll.await();
        val localResult = localCocktails.await()



        val mergedDataFirestoreAll =
        (firestoreCocktailsUserData.data?.toList() ?: listOf()) + (firestoreCocktailsAllData.data?.toList()
            ?: listOf())

        val mergedDataFirestoreByName =
            (firestoreCocktailsByNameData.data?.toList() ?: listOf()) + (firestoreCocktailsByNameAllData.data?.toList()
                ?: listOf())


        val firestoreResult =
            if (name1.isEmpty()) mergedDataFirestoreAll else mergedDataFirestoreByName

        // Combine the results of firestoreCocktails and localCocktails
        val mergedData =
            (firestoreResult ?: listOf()) + (localResult.data?.toList()
                ?: listOf())
        // Convert mergedData to a MutableList<Cocktail>
        val cocktailList = mergedData.filterIsInstance<Cocktail>().toMutableList()
        // Create a Cocktails object with the MutableList<Cocktail>
        val cocktails = Cocktails(cocktailList)
        cocktailData = DataRequestWrapper(state = "finished", data = cocktails)
    }
    ShowDataSearch(loadCocktails = cocktailData, navController = navController)
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
                Text("No cocktails found")
            }
        }
    }
}