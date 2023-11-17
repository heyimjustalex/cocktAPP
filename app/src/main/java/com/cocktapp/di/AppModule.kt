package com.cocktapp.di

import com.cocktapp.network.CocktailAPI
import com.cocktapp.utils.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


//@Module: This annotation is used to mark the class AppModule as a Dagger Hilt module.
// Modules are responsible for providing instances of classes that can be injected
// into other parts of your application.

// @InstallIn(SingletonComponent::class): This annotation specifies the component
// in which the module will be installed. In this case, it's installed in the SingletonComponent,
// which means the provided dependencies will have a singleton scope.

// Dagger Hilt module is responsible for providing a singleton instance of the CocktailAPI interface,
// configured with the necessary settings for making API calls related to cocktails in an Android application.
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideCocktailApi(): CocktailAPI {
        return Retrofit.Builder() // package that simplifies usage of HTTP
            .baseUrl(Const.BASE_URL) // our base api url
            .addConverterFactory(GsonConverterFactory.create()) // converter for json -> kotlin
            .build()
            .create(CocktailAPI::class.java)
    }
}

