package com.cocktapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun EmailInputField(modifier: Modifier,
                    emailState: MutableState<String>,
                    label:String = "e-mail",
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
        label = { Text(text = label) },
        singleLine = isSingleLine,
        enabled = enabled,
        textStyle = TextStyle(fontSize=15.sp,color= MaterialTheme.colorScheme.onSecondary) ,
        onValueChange = {valueState.value = it},
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType=keyboardType,imeAction=imeAction)


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
        label = { Text(text = label)},
        textStyle = TextStyle(fontSize=15.sp,color= MaterialTheme.colorScheme.onSecondary) ,
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth(),
        visualTransformation =   visualTransformation,
        trailingIcon = {isPasswordVisibleIcon(isPasswordVisible = isPasswordVisible)} ,
        keyboardOptions = KeyboardOptions(
            keyboardType= KeyboardType.Password,
            imeAction=imeAction),

        )
}

@Composable
fun isPasswordVisibleIcon(isPasswordVisible: MutableState<Boolean>) {
    IconButton(onClick = { isPasswordVisible.value = !isPasswordVisible.value }) {
        Icon(imageVector = Icons.Rounded.Edit, contentDescription = "Password Visibility" )
    }
}
