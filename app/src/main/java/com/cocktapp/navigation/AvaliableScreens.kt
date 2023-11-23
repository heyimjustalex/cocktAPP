package com.cocktapp.navigation

import android.util.Log
import okhttp3.Route


enum class AvaliableScreens {
    EntryLoadingScreen,
    MainScreen,
    MyCocktailsScreen,
    CocktailDetailsScreen,
    CocktailAddScreen,
    CocktailSearchScreen,
    LoginScreen,
    RegisterScreen;

    companion object {

        fun fromRoute(route: String): AvaliableScreens =
            when (route?.substringBefore("/")) {
                EntryLoadingScreen.name -> EntryLoadingScreen
                MainScreen.name -> MainScreen
                MyCocktailsScreen.name->MyCocktailsScreen
                CocktailDetailsScreen.name -> CocktailDetailsScreen
                CocktailAddScreen.name -> CocktailAddScreen
                CocktailSearchScreen.name -> CocktailSearchScreen
                LoginScreen.name -> LoginScreen
                RegisterScreen.name -> RegisterScreen
                null -> MainScreen
            else -> throw Exception("Route companion object navigation error")
        }

    }
}