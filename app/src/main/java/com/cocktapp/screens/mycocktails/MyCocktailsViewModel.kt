package com.cocktapp.screens.mycocktails

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cocktapp.model.Cocktails
import com.cocktapp.repository.CocktailRepository
import com.cocktapp.wrappers.DataRequestWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MyCocktailsViewModel @Inject constructor(private val cocktailRepository: CocktailRepository) :ViewModel() {

    // Its an example for external api, there should be firebase api to get only our cocktails
    // Im leaving it only to let you know how it's supposed to be done
    suspend fun getCocktailsByName(name:String = "a"): DataRequestWrapper<Cocktails, String, Exception> {
        return cocktailRepository.getCocktailsByName(name)
    }
    suspend fun getCocktailsByIngredients(ingredients:String = "a"): DataRequestWrapper<Cocktails, String, Exception> {
        return cocktailRepository.getCocktailsByIngredients(ingredients)
    }
}