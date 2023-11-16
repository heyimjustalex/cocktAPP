package com.cocktapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cocktapp.navigation.AvaliableScreens
import com.cocktapp.ui.theme.CocktailBlackColor
import com.cocktapp.ui.theme.CocktailLightGrayColor
import com.cocktapp.ui.theme.CocktailOrangeColor
import com.cocktapp.ui.theme.CocktailWhiteColor


@Composable
fun EmailInputField(modifier: Modifier,
                    emailState: MutableState<String>,
                    label:String = "email",
                    enabled:Boolean = true,
                    imeAction: ImeAction = ImeAction.Next,
                    onAction: KeyboardActions = KeyboardActions.Default

){
    InputField(modifier=modifier,
        valueState = emailState,
        label, enabled,
        imeAction=imeAction,
        onAction = onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier,
    valueState: MutableState<String>,
    label: String,
    enabled: Boolean,
    isSingleLine:Boolean=true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default

){

    OutlinedTextField(

        value = valueState.value,
        label = {Text(text = label, color = CocktailBlackColor)},
        singleLine = isSingleLine,
        enabled = enabled,
        textStyle = TextStyle(fontSize=15.sp,color= Color.Black) ,
        onValueChange = {valueState.value = it},
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType=keyboardType,imeAction=imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color(255, 255, 255),
            focusedBorderColor = CocktailBlackColor,
            unfocusedBorderColor = CocktailBlackColor,
            textColor = Color(0, 0, 0),
            focusedSupportingTextColor = Color(255, 255, 255),
            unfocusedSupportingTextColor = Color(255, 255, 255)

        )


    )
}


@Composable
fun HeaderLoginRegister(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text.uppercase(),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentSize(Alignment.Center),
        style = TextStyle(
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    )
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInputField(modifier: Modifier,
                       valueState: MutableState<String>,
                       label: String,
                       enabled: Boolean,
                       isSingleLine:Boolean = true,
                       isPasswordVisible: MutableState<Boolean>,
                       imeAction: ImeAction = ImeAction.Next,
                       onAction: KeyboardActions = KeyboardActions.Default


)

{

    val visualTransformation = if(!isPasswordVisible.value) PasswordVisualTransformation() else VisualTransformation.None

    OutlinedTextField(
        value = valueState.value,
        onValueChange ={valueState.value=it},
        singleLine = isSingleLine,
        enabled = enabled,
        label = {Text(text = label, color = CocktailBlackColor)},
        textStyle = TextStyle(fontSize=15.sp,color= Color.Black) ,
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
                ,
        visualTransformation =   visualTransformation,
        trailingIcon = {isPasswordVisibleIcon(isPasswordVisible = isPasswordVisible)} ,
        keyboardOptions = KeyboardOptions(
            keyboardType= KeyboardType.Password,
            imeAction=imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color(255, 255, 255),
            focusedBorderColor = CocktailBlackColor,
            unfocusedBorderColor = CocktailBlackColor,
            textColor = Color(0, 0, 0),
            focusedSupportingTextColor = Color(255, 255, 255),
            unfocusedSupportingTextColor = Color(255, 255, 255)

        )


        )

}

@Composable
fun isPasswordVisibleIcon(isPasswordVisible: MutableState<Boolean>) {
    IconButton(onClick = { isPasswordVisible.value = !isPasswordVisible.value }) {
        Icon(imageVector = Icons.Rounded.Edit, contentDescription = "Password Visibility" )
    }
}

@Composable
fun SubmitButtonField(text: String,
                      loading: Boolean,
                      inputsAreValid: Boolean,
                      onClick: ()->Unit,

) {
    Button(
        modifier= Modifier
            .width(150.dp)
            .height(60.dp)
            .padding(8.dp)

        ,
        colors = ButtonDefaults.buttonColors(
            containerColor = CocktailBlackColor,
            contentColor = CocktailWhiteColor,
            disabledContentColor = CocktailWhiteColor,
            disabledContainerColor = CocktailBlackColor
        ),
        onClick = onClick,
        enabled = !loading && inputsAreValid,
        shape = CircleShape,


    ){

        if(loading){
            CircularProgressIndicator(modifier=Modifier.size(20.dp))
        }
        else{
            Text(text = text, modifier=Modifier.padding(3.dp))
        }

    }

}

@Composable
fun BottomFormRedirectButton(navController: NavController, navRoute:String, text:String){

    Button(
        onClick = { navController.navigate(navRoute) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White,
            disabledContentColor = Color.Gray
        ),
        border = BorderStroke(1.dp, Color.White)
    ) {
        Text(
            text = text.uppercase(),
            style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(4.dp)
        )
    }

}

