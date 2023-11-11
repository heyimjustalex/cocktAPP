package com.cocktapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CocktailApplication:Application() {
}
//@HiltAndroidApp and :Application() inheritance
// It tells Dagger Hilt that this class should be treated as the
// application class and that Dagger Hilt should generate the necessary
// components and setup for dependency injection.


