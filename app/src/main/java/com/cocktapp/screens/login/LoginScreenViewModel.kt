package com.cocktapp.screens.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginScreenViewModel :ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    val loading: MutableState<Boolean> = mutableStateOf(false)

    fun logUserIn(email: String, password: String, onSuccess: () -> Unit) = viewModelScope.launch {
        loading.value = true
        delay(500)
        try {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("LOGIN", "Success logging in TASK: ${task.result}")
                    onSuccess()

                    loading.value = false
                } else {
                    Log.d("LOGIN", "Can't log in TASK: ${task.exception?.stackTrace.toString()}")

                    loading.value = false
                }
            }
        } catch (exception: Exception) {

            loading.value = false
            Log.d("LOGIN", "loginUser exception")
        }
    }
}