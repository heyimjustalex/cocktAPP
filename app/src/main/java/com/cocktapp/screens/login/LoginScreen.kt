package com.cocktapp.screens.login
import FetchingState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cocktapp.components.BottomFormRedirectButton
import com.cocktapp.components.EmailInputField
import com.cocktapp.components.HeaderLoginRegister
import com.cocktapp.components.PasswordInputField
import com.cocktapp.components.SubmitButtonField
import com.cocktapp.navigation.AvaliableScreens
import com.cocktapp.ui.theme.CocktailBlackColor
import com.cocktapp.ui.theme.CocktailDarkGrayColor
import com.cocktapp.ui.theme.CocktailOrangeColor
import com.cocktapp.ui.theme.CocktailWhiteColor

@Composable
fun LoginScreen(navController: NavController, loginScreenViewModel: LoginScreenViewModel = viewModel()){

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(CocktailOrangeColor)

                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HeaderLoginRegister(text = "Welcome")
            HeaderLoginRegister(text = "Please Login or Register")
            LoginForm(loginScreenViewModel = loginScreenViewModel, navController = navController)
            Column (modifier=Modifier.padding(0.dp,50.dp,0.dp,0.dp)){


            BottomFormRedirectButton(navController,AvaliableScreens.RegisterScreen.name,"Click here to sign up!")
            }
        }
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginForm(
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

    val passwordFocus = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val isValid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty() && password.value.trim().length>=6
    }
    val stateValue = loginScreenViewModel.state.value
    val modifier = Modifier

        .background(CocktailOrangeColor)
        .verticalScroll(rememberScrollState())

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        EmailInputField(
            modifier = Modifier,
            emailState = email,
            enabled = stateValue!==FetchingState.LOADING,
            onAction = KeyboardActions {
                passwordFocus.requestFocus()
            }
        )
        PasswordInputField(
            modifier = Modifier.focusRequester(passwordFocus),
            valueState = password,
            label = "password",
            enabled = stateValue!==FetchingState.LOADING,
            isPasswordVisible = isPasswordVisible,

            onAction = KeyboardActions {
                if (!isValid) return@KeyboardActions
                else {
//                    run {
//                        email.value.trim()
//                        password.value.trim()
//                    }
                    if (keyboardController != null) {
                        keyboardController.hide()

                    }
                }
            }
        )

        RenderProperStateChangeReaction(loginScreenViewModel.state)

        SubmitButtonField(
            text = "Login",
            loading = stateValue==FetchingState.LOADING,
            inputsAreValid = isValid,
            containerColor = CocktailBlackColor,
            contentColor = CocktailWhiteColor,
            disabledContentColor = CocktailBlackColor,
            disabledContainerColor = CocktailDarkGrayColor,
            onClick = {
//                run {
//                    email.value.trim()
//                    password.value.trim()
//                }
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




@Composable
fun RenderProperStateChangeReaction(state: MutableState<FetchingState>){
    if(state.value==FetchingState.LOADING){
        Text(text = state.value.message)
    //    CircularProgressIndicator()
    }
    else {
        Text(text = state.value.message)
    }
}


