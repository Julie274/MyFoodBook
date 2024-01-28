package fr.soetewey.myfoodbook.data

data class Recipe(
    val id: Int,
    val name: String,
    val image: String,
    val ingredients: List<Ingredient>,
    val steps: List<String>
)
