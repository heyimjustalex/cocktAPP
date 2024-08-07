package com.cocktapp.screens.searchcocktails

import androidx.lifecycle.ViewModel
import com.cocktapp.model.Cocktails
import com.cocktapp.repository.CocktailRepository
import com.cocktapp.repository.CocktailFirestoreRepository
import com.cocktapp.wrappers.DataRequestWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class CocktailSearchViewModel @Inject constructor(private val cocktailRepository: CocktailRepository, private val cocktailFirestoreRepository :CocktailFirestoreRepository) :
    ViewModel() {

    // There you have some functions calling repository for external api
    // so you know how to fetch data in order to search it
    // if you want to see how to process it you need to look at DataRequestWrapper, Repository, Model, CocktailAPI Classes

    suspend fun getCocktailsFirestoreByName(name: String): DataRequestWrapper<Cocktails, String, Exception> {
        return cocktailFirestoreRepository.getCocktailsFirestoreByName(name)
    }

    suspend fun getCocktailsFirestoreByNameAll(name:String): DataRequestWrapper<Cocktails, String, Exception> {
        return cocktailFirestoreRepository.getCocktailsFirestoreByNameAll(name)
    }

    suspend fun getCocktailsByName(name:String): DataRequestWrapper<Cocktails, String, Exception> {
        return cocktailRepository.getCocktailsByName(name)
    }

    suspend fun getCocktailsFirestoreUser(): DataRequestWrapper<Cocktails, String, Exception> {
        return cocktailFirestoreRepository.getCocktailsFirestore()
    }

    suspend fun getCocktailsFirestoreAll(): DataRequestWrapper<Cocktails, String, Exception> {
        return cocktailFirestoreRepository.getCocktailsFirestoreAllCocktails()
    }
}
