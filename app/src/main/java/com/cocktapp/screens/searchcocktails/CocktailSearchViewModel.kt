package com.cocktapp.screens.searchcocktails

import androidx.lifecycle.ViewModel
import com.cocktapp.model.Cocktails
import com.cocktapp.repository.CocktailRepository
import com.cocktapp.wrappers.DataRequestWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class CocktailSearchViewModel @Inject constructor(private val cocktailRepository: CocktailRepository) :
    ViewModel() {

    // There you have some functions calling repository for external api
    // so you know how to fetch data in order to search it
    // if you want to see how to process it you need to look at DataRequestWrapper, Repository, Model, CocktailAPI Classes

    suspend fun getCocktailsByName(name:String = "a"): DataRequestWrapper<Cocktails, String, Exception> {
        return cocktailRepository.getCocktailsByName(name)
    }
    suspend fun getCocktailsByIngredients(ingredients:String = "a"): DataRequestWrapper<Cocktails, String, Exception> {
        return cocktailRepository.getCocktailsByIngredients(ingredients)
    }
}