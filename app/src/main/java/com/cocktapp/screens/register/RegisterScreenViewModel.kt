package com.cocktapp.screens.register



import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch

class RegisterScreenViewModel :ViewModel() {


    private val auth: FirebaseAuth = Firebase.auth

    val loading: MutableState<Boolean> = mutableStateOf(false)



    fun registerUserWithEmailAndPassword(
        email:String,
        password:String,
        onSuccess:()->Unit

    ) = viewModelScope.launch {

        if (loading.value==false){
            loading.value=true
            delay(500)
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->

                if(task.isSuccessful){
                    onSuccess()
                }
                else
                {
                    Log.d("Register",task.result.toString())
                }

            }
            loading.value = false
        }


    }

}