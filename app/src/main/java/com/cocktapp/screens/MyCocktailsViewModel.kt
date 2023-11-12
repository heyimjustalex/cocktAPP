package com.cocktapp.screens

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
    suspend fun getCocktails(query:String): DataRequestWrapper<Cocktails, String, Exception> {
        return cocktailRepository.getCocktails(query)
    }
}