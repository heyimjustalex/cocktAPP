package com.cocktapp.screens.register
import FetchingState
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cocktapp.model.FUser
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
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
        delay(400)

        try {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    state.value = FetchingState.SUCCESS.withMessage("Success!")
                    val displayName = task.result.user?.email?.split('@')?.get(0)
                    createUserInDifferentCollection(displayName)
                    onSuccess()
                }
                else {
                    val message = getMessageForFirebaseExceptionRegister(task.exception)
                    state.value = FetchingState.FAILED_INSTANCE.withMessage(message)
                    Log.d("Register", task.exception.toString())

                }
            }
        } catch (e: Exception) {
            val message = getMessageForFirebaseExceptionRegister(e)
            state.value = FetchingState.FAILED.withMessage(message)
            Log.e("Register", "Exception during user registration", e)
        }
    }
    // V1
    /*fun createUserInDifferentCollection(displayName:String?){
        val userId = auth.currentUser?.uid
        val user = displayName?.let { FUser(id=userId,displayName= it) }?.toMap()

        if (user != null) {
            FirebaseFirestore.getInstance().collection("users").add(user)
        }
    }*/

    // V2
    /*fun createUserInDifferentCollection(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = displayName?.let { FUser(id = userId, displayName = it) }

        if (user != null) {
            val usersCollection = FirebaseFirestore.getInstance().collection("users")

            usersCollection.add(user.toMap()).addOnSuccessListener { documentReference ->
                val newUserId = documentReference.id
                usersCollection.document(newUserId).collection("myCocktails")
            }
        }
    }*/
    fun createUserInDifferentCollection(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = displayName?.let { FUser(id = userId, displayName = it) }

        if (user != null && userId != null) {
            val usersCollection = FirebaseFirestore.getInstance().collection("users")

            // We add the user with same document id as user id
            usersCollection.document(userId).set(user.toMap())
        }
    }


}

fun getMessageForFirebaseExceptionRegister(exception: Exception?): String {
    var exceptionMessage = ""
    if (exception != null) {
        exceptionMessage = exception.localizedMessage.orEmpty()
        Log.d("EXC", exception.message.toString())
    }
    return when {
        exceptionMessage.contains("already", true) -> {
            "User already exists"
        }
        exceptionMessage.contains("code", true) -> {
            "Code expired"
        }
        exceptionMessage.contains("password", true) -> {
            "Weak password"        }

        else -> {
            "Firebase registration failed, try again later"
        }
    }
}