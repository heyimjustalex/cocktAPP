package com.cocktapp.screens.addCocktail

import FetchingState
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cocktapp.components.InputField
import com.cocktapp.components.ResizableMultiLineInputField
import com.cocktapp.components.SubmitButtonField
import com.cocktapp.navigation.EditableNavbarForScaffoldWithLogoutAndBackButton
import com.cocktapp.screens.register.RegisterScreenViewModel
import com.cocktapp.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


data class CocktailFormData(
    val ingredients: MutableList<String>,
    val inputIngredients: MutableState<String>,
    val inputPreparation: MutableState<String>,
    val cocktailName: MutableState<String>,
    val showSuccessMessage: MutableState<Boolean> = mutableStateOf(false)
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CocktailAddScreen(navController: NavController, viewModel: CocktailAddViewModel = hiltViewModel()) {
    var formData by remember {
        mutableStateOf(
            CocktailFormData(
                mutableStateListOf(),
                mutableStateOf(""),
                mutableStateOf(""),
                mutableStateOf("New Cocktail")
            )
        )
    }

    val isRecipePrivate = remember { mutableStateOf(false) }
    val registerScreenViewModel: RegisterScreenViewModel = viewModel()
    val stateValue = registerScreenViewModel.state.value

    Scaffold(
        topBar = {
            EditableNavbarForScaffoldWithLogoutAndBackButton(
                navController = navController,
                defaultTitle = "New Cocktail",
                onSave = { newValue -> formData.cocktailName.value = newValue }
            )
        },
        contentColor = Color.Black
    ) {
        Column(
            modifier = Modifier
                .padding(30.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Ingredients(formData.ingredients, formData.inputIngredients, stateValue)
            Preparation(formData.inputPreparation, isRecipePrivate)

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                SubmitButtonField(
                    text = "Create cocktail",
                    loading = stateValue == FetchingState.LOADING,
                    inputsAreValid = inputIsValid(formData),
                    containerColor = CocktailOrangeColor,
                    contentColor = CocktailBlackColor,
                    disabledContentColor = CocktailBlackColor,
                    disabledContainerColor = CocktailDarkGrayColor,
                    onClick = {
                        val flatCocktailData = mapOf(
                            "ingredients" to formData.ingredients.toList(),
                            "instructions" to formData.inputPreparation.value,
                            "name" to formData.cocktailName.value.lowercase()
                        )

                        CoroutineScope(Dispatchers.Main).launch {
                            viewModel.addCocktail(flatCocktailData, isRecipePrivate.value)
                        }

                        formData.showSuccessMessage.value = true

                    }
                )
            }

            LaunchedEffect(formData.showSuccessMessage.value) {
                delay(3000)
                formData.showSuccessMessage.value = false
                formData = CocktailFormData(
                    mutableStateListOf(),
                    mutableStateOf(""),
                    mutableStateOf(""),
                    mutableStateOf("New Cocktail")
                )
                isRecipePrivate.value = false
            }

            if (formData.showSuccessMessage.value) {
                Text(
                    text = "Cocktail added successfully!",
                    color = CocktailOrangeColor,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
fun inputIsValid(formData: CocktailFormData): Boolean {
    return formData.cocktailName.value.isNotBlank() &&
            formData.ingredients.all { it.isNotBlank() } &&
            formData.inputPreparation.value.isNotBlank()
}

@Composable
fun Ingredients(
    ingredients: MutableList<String>,
    inputIngredients: MutableState<String>,
    stateValue: FetchingState
) {
    Row(
        modifier = Modifier
            .padding(top = 50.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(top = 7.dp)
        ) {
            Text(
                text = "Ingredients",
                style = LocalTextStyle.current.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SubmitButtonField(
                text = "Add",
                loading = stateValue == FetchingState.LOADING,
                inputsAreValid = inputIngredients.value.isNotBlank(),
                containerColor = CocktailOrangeColor,
                contentColor = CocktailBlackColor,
                disabledContentColor = CocktailBlackColor,
                disabledContainerColor = CocktailDarkGrayColor,
                onClick = {
                    ingredients.add(inputIngredients.value)
                    inputIngredients.value = ""
                }
            )
        }
    }

    InputField(
        modifier = Modifier,
        valueState = inputIngredients,
        label = "Enter new ingredient",
        enabled = true,
        backgroundColor = CocktailLightGrayColor
    )

    Spacer(modifier = Modifier.height(8.dp))

    for ((index, ingredient) in ingredients.withIndex()) {
        Text(
            text = ingredient,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        )

        if (index < ingredients.size - 1) {
            Divider(
                color = CocktailBlackColor,
                thickness = 2.dp,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .padding(bottom = 5.dp)
            )
        }
    }
}

@Composable
fun Preparation(inputPreparation: MutableState<String>, isRecipePrivate: MutableState<Boolean>) {
    Text(
        text = "Preparation",
        style = LocalTextStyle.current.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        ),
        modifier = Modifier
            .padding(top = 20.dp)
            .padding(bottom = 10.dp)
            .fillMaxWidth()
    )

    ResizableMultiLineInputField(
        value = inputPreparation.value,
        onValueChange = { newValue -> inputPreparation.value = newValue },
        label = "Enter how to prepare this drink",
        backgroundColor = CocktailLightGrayColor
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isRecipePrivate.value,
            onCheckedChange = { isChecked ->
                isRecipePrivate.value = isChecked
            },
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Text(
            text = "Private",
            style = LocalTextStyle.current.copy(
                fontSize = 16.sp
            ),
        )
    }
}
