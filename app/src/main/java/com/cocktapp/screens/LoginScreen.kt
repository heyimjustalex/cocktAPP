package com.cocktapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cocktapp.components.EmailInputField
import com.cocktapp.components.PasswordInputField

@Composable
fun LoginScreen(navController: NavController){

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text("This is login screen")
            LoginForm()
        }

    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginForm(
    loading:Boolean = false,
    isCreateAccount:Boolean = false,
    onDone:(String,String) -> Unit = { s: String, s1: String -> }


){
    val email = rememberSaveable {
        mutableStateOf("")
    }

    val password= rememberSaveable {
        mutableStateOf("")

    }

    val isPasswordVisible= rememberSaveable {
        mutableStateOf(false)
    }

    val passwordFocus = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val isValid = remember(email.value, password.value){
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    val modifier = Modifier
        .height(260.dp)
        .background(Color.White)
        .verticalScroll(rememberScrollState())

    Column(
        modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        EmailInputField(
            modifier = Modifier,
            emailState = email,
            enabled = !loading,
            onAction = KeyboardActions{
                passwordFocus.requestFocus()
            }
        )
        PasswordInputField(
            modifier=Modifier.focusRequester(passwordFocus),
            valueState = password,
            label = "password",
            enabled = !loading,
            isPasswordVisible = isPasswordVisible,

            onAction = KeyboardActions{
                if(!isValid) return@KeyboardActions
                else
                {
                    onDone(email.value.trim(),password.value.trim())
                }
            }
        )

    }

}

