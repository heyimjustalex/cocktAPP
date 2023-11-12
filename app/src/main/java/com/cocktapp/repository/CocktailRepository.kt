package com.cocktapp.repository

import android.util.Log
import com.cocktapp.model.Cocktails
import com.cocktapp.network.CocktailAPI
import com.cocktapp.wrappers.DataRequestWrapper
import javax.inject.Inject



class CocktailRepository @Inject constructor(private val api: CocktailAPI) {
    suspend fun getCocktailsByName(name:String) : DataRequestWrapper<Cocktails, String, Exception>{

        val response =
            try{
                api.getCocktailsByName(name)

            }
            catch (e:Exception){
                Log.d("RESPONSE",e.stackTraceToString())
                return DataRequestWrapper(exception = e)
            }


        return DataRequestWrapper(data = response)
    }
    suspend fun getCocktailsByIngredients(ingredients:String) : DataRequestWrapper<Cocktails, String, Exception>{

        val response =
            try{
                api.getCocktailsByIngredients(ingredients = ingredients )
            }
            catch (e:Exception){
                return DataRequestWrapper(exception = e)
            }
        return DataRequestWrapper(data = response)
    }
}