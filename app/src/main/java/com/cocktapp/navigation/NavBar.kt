package com.cocktapp.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cocktapp.R
import com.cocktapp.ui.theme.CocktailBlackColor
import com.cocktapp.ui.theme.CocktailOrangeColor
import com.google.firebase.auth.FirebaseAuth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavbarForScaffoldWithLogout(navController: NavController, text: String) {

    TopAppBar(
        title = { Text(text = text) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Black,
            titleContentColor = CocktailOrangeColor
        ),
        actions = {
            logoutIcon(navController = navController)

        }
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavbarForScaffoldWithLogoutAndBackButton(navController: NavController, text: String) {

    TopAppBar(
        title = { Text(text = text) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = CocktailBlackColor,
            titleContentColor = CocktailOrangeColor
        ),
        navigationIcon = {
            navIcon(navController)
        },
        actions = {
            logoutIcon(navController = navController)

        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableNavbarForScaffoldWithLogoutAndBackButton(
    navController: NavController,
    defaultTitle: String,
    onSave: (String) -> Unit
) {
    var editableTitle by remember { mutableStateOf(defaultTitle) }
    var isEditing by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            TextField(
                value = editableTitle,
                onValueChange = {
                    editableTitle = it
                },
                textStyle = LocalTextStyle.current.copy(
                    color = CocktailOrangeColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CocktailBlackColor)
                    .clickable(enabled = isEditing) {
                        isEditing = true
                    }
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = CocktailBlackColor,
            titleContentColor = CocktailOrangeColor
        ),
        navigationIcon = {
            navIcon(navController)
        },
        actions = {
            IconButton(
                onClick = {
                    if (isEditing) {
                        onSave(editableTitle)
                        isEditing = false
                    } else {
                        isEditing = true
                    }
                }
            ) {
                Icon(
                    imageVector = if (isEditing) Icons.Default.Check else Icons.Default.Edit,
                    contentDescription = "Edit/Save",
                    tint = CocktailOrangeColor
                )
            }
            logoutIcon(navController = navController)
        },
    )
}


@Composable
fun navIcon(navController: NavController) {
    Row(horizontalArrangement = Arrangement.Start) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "Back arrow",
                tint = CocktailOrangeColor
            )
        }
    }
}

@Composable
fun logoutIcon(navController: NavController) {
    Row(horizontalArrangement = Arrangement.Start) {
        IconButton(onClick = {
            FirebaseAuth.getInstance().signOut().run {
                navController.navigate(AvaliableScreens.LoginScreen.name)
            }

        }) {
            Image(
                modifier = Modifier.size(18.dp),
                contentScale = ContentScale.Fit,
                painter = painterResource(R.drawable.logout),
                contentDescription = "Logout",
                colorFilter = ColorFilter.tint(CocktailOrangeColor)
            )
        }
    }
}