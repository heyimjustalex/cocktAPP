package com.cocktapp.repository

import android.util.Log
import com.cocktapp.model.Cocktail
import com.cocktapp.model.Cocktails
import com.cocktapp.wrappers.DataRequestWrapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CocktailFirestoreRepository @Inject constructor() {

    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getCocktailsFirestore() : DataRequestWrapper<Cocktails, String, Exception> {

        val response =
            try{
                val result = firestore.collection("myCocktails")
                    .get()
                    .await()

                Cocktails(result.toObjects(Cocktail::class.java))

            }
            catch (e:Exception){
                Log.d("RESPONSE",e.stackTraceToString())
                return DataRequestWrapper(exception = e)
            }

        return DataRequestWrapper(data = response)
    }
}