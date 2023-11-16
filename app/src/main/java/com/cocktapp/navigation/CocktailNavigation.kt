package com.cocktapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cocktapp.screens.entryloadingscreen.EntryLoadingScreen
import com.cocktapp.screens.login.LoginScreen
import com.cocktapp.screens.main.MainScreen
import com.cocktapp.screens.mycocktails.MyCocktailsScreen
import com.cocktapp.screens.register.RegisterScreen
import com.cocktapp.screens.searchcocktails.CocktailSearchScreen

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
    }
}