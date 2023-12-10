package com.cocktapp.screens.addCocktail

import androidx.lifecycle.ViewModel
import com.cocktapp.repository.CocktailFirestoreRepository
import com.cocktapp.wrappers.DataRequestWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CocktailAddViewModel
@Inject constructor(private val cocktailFirestoreRepository : CocktailFirestoreRepository) :  ViewModel(){

    suspend fun addCocktail(cocktailData: Map<String, Any>, isRecipePrivate: Boolean) : DataRequestWrapper<Unit, String, Exception> {
        return cocktailFirestoreRepository.addCocktail(cocktailData, isRecipePrivate)
    }
}