package com.cocktapp.repository

import android.util.Log
import com.cocktapp.model.Cocktail
import com.cocktapp.model.Cocktails
import com.cocktapp.wrappers.DataRequestWrapper
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CocktailFirestoreRepository @Inject constructor() {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth: FirebaseAuth = Firebase.auth
    suspend fun getCocktailsFirestore() : DataRequestWrapper<Cocktails, String, Exception> {

        /*val response =
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

        return DataRequestWrapper(data = response)*/
        val userId = auth.currentUser?.uid
        val response =
            try {
                if (userId != null) {
                    val result = firestore.collection("users")
                        .document(userId)
                        .collection("myCocktails")
                        .get()
                        .await()

                    val cocktails = result.toObjects(Cocktail::class.java).map { cocktail ->
                        cocktail.copy(fromWhere = "Firestore")
                    }.toMutableList()

                    Cocktails(cocktails)
                } else {
                    throw Exception("User ID is null.")
                }
            } catch (e: Exception) {
                Log.d("RESPONSE", e.stackTraceToString())
                return DataRequestWrapper(exception = e)
            }

        return DataRequestWrapper(data = response)
    }

    suspend fun getCocktailsFirestoreByName(name: String) : DataRequestWrapper<Cocktails, String, Exception> {

        val response =
            try{
                val result = firestore.collection("myCocktails")
                    .whereEqualTo("name", name)
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