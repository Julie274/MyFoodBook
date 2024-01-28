package fr.soetewey.myfoodbook.data

data class Recipe(
    val name: String,
    val image: String,
    val ingredients: List<Ingredient>,
    val steps: List<String>
)
