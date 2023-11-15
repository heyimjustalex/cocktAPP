package com.cocktapp.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cocktapp.components.BottomFormRedirectButton
import com.cocktapp.components.EmailInputField
import com.cocktapp.components.PasswordInputField
import com.cocktapp.components.SubmitButtonField
import com.cocktapp.navigation.AvaliableScreens
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(navController: NavController, loginScreenViewModel: LoginScreenViewModel = viewModel()){

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("This is the login screen")
            LoginForm(loginScreenViewModel = loginScreenViewModel, navController = navController)

            BottomFormRedirectButton(navController,AvaliableScreens.RegisterScreen.name,"Click here to sign up!")
        }
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginForm(
    loading:Boolean = false,
    onDone:(String,String) -> Unit = { s: String, s1: String -> },
    loginScreenViewModel: LoginScreenViewModel,
    navController:NavController

) {
    val email = rememberSaveable {
        mutableStateOf("")
    }

    val password = rememberSaveable {
        mutableStateOf("")

    }

    val isPasswordVisible = rememberSaveable {
        mutableStateOf(false)
    }

    val passwordFocus = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val isValid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty() && password.value.trim().length>=6
    }

    val modifier = Modifier

        .background(Color.White)
        .height(370.dp)
        .verticalScroll(rememberScrollState())

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        EmailInputField(
            modifier = Modifier,
            emailState = email,
            enabled = !loading,
            onAction = KeyboardActions {
                passwordFocus.requestFocus()
            }
        )
        PasswordInputField(
            modifier = Modifier.focusRequester(passwordFocus),
            valueState = password,
            label = "password",
            enabled = !loading,
            isPasswordVisible = isPasswordVisible,

            onAction = KeyboardActions {
                if (!isValid) return@KeyboardActions
                else {
                    onDone(email.value.trim(), password.value.trim())
                }
            }
        )

        if(loginScreenViewModel.loading.value==true){
            CircularProgressIndicator()
        }

        SubmitButtonField(
            text = "Login",
            loading = loading,
            inputsAreValid = isValid,
            onClick = {
                onDone(email.value.trim(), password.value.trim())
                if (keyboardController != null) {
                    keyboardController.hide()
                }
                loginScreenViewModel.logUserIn(email.value.trim(), password.value.trim(),
                    onSuccess = {navController.navigate(AvaliableScreens.MainScreen.name)}

                    )



            }

        )



    }

}






