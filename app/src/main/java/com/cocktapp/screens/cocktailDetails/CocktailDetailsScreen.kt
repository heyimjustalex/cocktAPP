package com.cocktapp.screens.cocktailDetails

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.cocktapp.R
import com.cocktapp.model.Cocktail
import com.cocktapp.navigation.NavbarForScaffoldWithLogoutAndBackButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailDetailsScreen(navController: NavController, cocktailString: String) {

    // Extract Ingredients form cocktailString
    val ingredientsRegex = Regex("""ingredients=\[([^\]]+)\]""")
    val ingredientsMatch = ingredientsRegex.find(cocktailString)
    val ingredientsList = ingredientsMatch?.groupValues?.get(1)?.split(", ") ?: emptyList()

    // Extract Instructions form cocktailString
    val instructionsRegex = Regex("""instructions=([^,]+),""")
    val instructionsMatch = instructionsRegex.find(cocktailString)
    val instructions = instructionsMatch?.groupValues?.get(1) ?: ""

    // Extract Name form cocktailString
    val nameRegex = Regex("""name=([^,]+)(?:\))""")
    val nameMatch = nameRegex.find(cocktailString)
    val name = nameMatch?.groupValues?.get(1)?.trim() ?: ""

    Scaffold(
        topBar = { NavbarForScaffoldWithLogoutAndBackButton(navController = navController, "Cocktail Details") },
        contentColor = Color.Black


    ) {
        Surface(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            color = Color.White,

            ) {
            Column(
                modifier = Modifier.padding(2.dp),
                //horizontalAlignment = Alignment.CenterHorizontally,
                //verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.padding(top = 80.dp, bottom = 40.dp)
                ) {
                    Text(
                        text = name,
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Row {
                    Text(
                        text = "INSTRUCTIONS",
                        color = Color.Black,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Row(
                    modifier = Modifier.padding(bottom = 30.dp)
                ) {
                    Text(
                        text = instructions
                    )
                }

                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(bottom = 30.dp)
                )


                Row {
                    Text(
                        text = "INGREDIENTS",
                        color = Color.Black,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                LazyColumn {
                    items(ingredientsList.size) { index ->
                        Text(
                            text = ingredientsList[index],
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Left,
                        )
                    }
                }
                Box {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .offset(x = 300.dp, y = 120.dp)
                            .size(70.dp),

                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF161616),
                            contentColor = Color.White)

                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.share), // SVG-Icon als Vektor-Drawable
                            contentDescription = "Icon"
                        )
                    }
                }
            }


                //Text(text = "INSTRUCTIONS -> ${instructions}", color = Color.Black)
                //Text(text = "INGREDIENTS -> ${ingredientsList}", color = Color.Black)
        }
    }
}