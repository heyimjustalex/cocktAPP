package com.cocktapp.network

import com.cocktapp.model.Cocktails
import com.cocktapp.utils.Const
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton

interface CocktailAPI{

    @GET(value ="v1/cocktail")
    @Headers(Const.API_HEADER+":"+Const.API_KEY)
    suspend fun getCocktailsByName(
        @Query("name") name:String,
        ):Cocktails

    @GET(value ="v1/cocktail")
    @Headers(Const.API_HEADER+":"+Const.API_KEY)
    suspend fun getCocktailsByIngredients(
        @Query("ingredients") ingredients:String = "",

        ):Cocktails
}