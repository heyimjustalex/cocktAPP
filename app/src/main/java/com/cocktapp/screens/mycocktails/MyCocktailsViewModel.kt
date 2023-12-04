package com.cocktapp.screens.mycocktails

import androidx.lifecycle.ViewModel
import com.cocktapp.model.Cocktails
import com.cocktapp.repository.CocktailFirestoreRepository
import com.cocktapp.wrappers.DataRequestWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MyCocktailsViewModel @Inject constructor(private val cocktailRepository: CocktailFirestoreRepository) :
    ViewModel() {

    suspend fun getCocktailsFirestore(): DataRequestWrapper<Cocktails, String, Exception> {
        return cocktailRepository.getCocktailsFirestore()
    }
}