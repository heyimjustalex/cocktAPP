package com.cocktapp.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cocktapp.screens.entryloadingscreen.EntryLoadingScreen
import com.cocktapp.screens.login.LoginScreen
import com.cocktapp.screens.main.MainScreen
import com.cocktapp.screens.mycocktails.MyCocktailsScreen
import com.cocktapp.screens.register.RegisterScreen
import com.cocktapp.screens.searchcocktails.CocktailSearchScreen
import com.cocktapp.screens.cocktailDetails.CocktailDetailsScreen
import com.cocktapp.screens.addCocktail.CocktailAddScreen

@Composable
fun CocktailNavigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AvaliableScreens.EntryLoadingScreen.name
    )
    {
        composable( AvaliableScreens.EntryLoadingScreen.name){
            EntryLoadingScreen(navController)
        }
        composable( AvaliableScreens.MainScreen.name){
            MainScreen(navController)
        }
        composable( AvaliableScreens.MyCocktailsScreen.name){
            MyCocktailsScreen(navController)
        }
        composable( AvaliableScreens.LoginScreen.name){
            LoginScreen(navController)
        }
        composable( AvaliableScreens.RegisterScreen.name){
            RegisterScreen(navController)
        }
        composable( AvaliableScreens.CocktailSearchScreen.name){
            CocktailSearchScreen(navController)
        }
        composable( AvaliableScreens.CocktailAddScreen.name){
            CocktailAddScreen(navController)
        }
        composable(
            "${AvaliableScreens.CocktailDetailsScreen.name}/?cocktail={cocktail}",
            arguments = listOf(
                navArgument("cocktail") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val encodedCocktail = backStackEntry.arguments?.getString("cocktail")
            val decodedCocktail = Uri.decode(encodedCocktail)
            CocktailDetailsScreen(navController, decodedCocktail)
        }

    }
}