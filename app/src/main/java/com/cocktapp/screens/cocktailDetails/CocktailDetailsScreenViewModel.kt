package com.cocktapp.screens.cocktailDetails

import androidx.lifecycle.ViewModel
import com.cocktapp.model.Cocktails
import com.cocktapp.repository.CocktailFirestoreRepository
import com.cocktapp.wrappers.DataRequestWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CocktailDetailScreenViewModel @Inject constructor(private val cocktailRepository: CocktailFirestoreRepository) :
    ViewModel() {

    suspend fun deleteCocktailFirestore(cocktailId: String): DataRequestWrapper<Unit, String, Exception> {
        return cocktailRepository.deleteCocktail(cocktailId = cocktailId);
    }

}