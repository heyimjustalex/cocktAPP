package com.cocktapp.screens.register



import FetchingState
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
import com.google.firebase.auth.FirebaseAuthActionCodeException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch

class RegisterScreenViewModel :ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    val state: MutableState<FetchingState> = mutableStateOf(FetchingState.IDLE)
    fun registerUserWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit
    ) = viewModelScope.launch {

        state.value = FetchingState.LOADING.withMessage("Wait...")
        delay(3000)

        try {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    state.value = FetchingState.SUCCESS.withMessage("Success!")
                    onSuccess()
                }
                else {
                    val message = getMessageForFirebaseException(task.exception)
                    state.value = FetchingState.FAILED_INSTANCE.withMessage(message)
                    Log.d("Register", task.exception.toString())

                }
            }
        } catch (e: Exception) {
            val message = getMessageForFirebaseException(e)
            state.value = FetchingState.FAILED.withMessage(message)
            Log.e("Register", "Exception during user registration", e)
        }
    }

}

fun getMessageForFirebaseException(exception: Exception?): String {
    when (exception) {
        is FirebaseAuthUserCollisionException -> {
            return "User already exists"
        }

        is FirebaseAuthActionCodeException -> {
            return "Code expired"
        }
        is FirebaseAuthException -> {
            return "Firebase auth register failed"
        }
        else -> {
            return "Firebase failed, try again later"
        }
    }
}