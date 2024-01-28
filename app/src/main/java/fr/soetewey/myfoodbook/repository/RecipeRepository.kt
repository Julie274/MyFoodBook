package fr.soetewey.myfoodbook.repository

import fr.soetewey.myfoodbook.data.Recipe

interface RecipeRepository {
    var listRecipe: List<Recipe>

    suspend fun addRecipe(recipe: Recipe)

    fun removeRecipe(recipe: Recipe)
}