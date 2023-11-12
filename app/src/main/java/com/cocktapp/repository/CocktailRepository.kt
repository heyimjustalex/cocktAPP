package com.cocktapp.repository

import com.cocktapp.model.Cocktails
import com.cocktapp.network.CocktailAPI
import com.cocktapp.wrappers.DataRequestWrapper
import javax.inject.Inject



class CocktailRepository @Inject constructor(private val api: CocktailAPI) {
    suspend fun getCocktails(query: String) : DataRequestWrapper<Cocktails, String, Exception>{

        val response =
            try{
                api.getCocktails(query)
            }
            catch (e:Exception){
                return DataRequestWrapper(exception = e)
            }
        return DataRequestWrapper(data = response)
    }
}