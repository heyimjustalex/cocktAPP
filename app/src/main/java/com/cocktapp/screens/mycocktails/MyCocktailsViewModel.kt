package com.cocktapp.screens.mycocktails

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cocktapp.model.Cocktail
import com.cocktapp.model.Cocktails
import com.cocktapp.repository.CocktailFirestoreRepository
import com.cocktapp.repository.CocktailRepository
import com.cocktapp.wrappers.DataRequestWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MyCocktailsViewModel @Inject constructor(private val cocktailRepository: CocktailFirestoreRepository) :
    ViewModel() {

    suspend fun getCocktailsFirestore(): DataRequestWrapper<Cocktails, String, Exception> {
        return cocktailRepository.getCocktailsFirestore()
    }
}