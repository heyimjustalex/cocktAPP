package com.cocktapp.screens.searchcocktails

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.cocktapp.model.Cocktail
import com.cocktapp.model.Cocktails
import com.cocktapp.navigation.NavbarForScaffoldWithLogoutAndBackButton
import com.cocktapp.screens.cocktails.ShowDataSearch
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
                        .padding(horizontal = 5.dp),
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
        val firestoreCocktails = async {
            cocktailSearchViewModel.getCocktailsFirestoreByName(name1)
        }
        val firestoreCocktailsAll = async {
            cocktailSearchViewModel.getCocktailsFirestoreAll()
        }
        val localCocktails = async { cocktailSearchViewModel.getCocktailsByName(name2) }
        val firestoreResult =
            if (name1.isEmpty()) firestoreCocktailsAll.await() else firestoreCocktails.await()
        val localResult = localCocktails.await()
        // Combine the results of firestoreCocktails and localCocktails
        val mergedData =
            (firestoreResult.data?.toList() ?: listOf()) + (localResult.data?.toList()
                ?: listOf())
        // Convert mergedData to a MutableList<Cocktail>
        val cocktailList = mergedData.filterIsInstance<Cocktail>().toMutableList()
        // Create a Cocktails object with the MutableList<Cocktail>
        val cocktails = Cocktails(cocktailList)
        cocktailData = DataRequestWrapper(state = firestoreResult.state, data = cocktails)
    }
    ShowDataSearch(loadCocktails = cocktailData, navController = navController)
}
