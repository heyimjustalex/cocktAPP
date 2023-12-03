package com.cocktapp.model

data class Cocktail(
    val ingredients: List<String> = emptyList(),
    val instructions: String = "",
    val name: String = "",
    val fromWhere: String = ""
)
