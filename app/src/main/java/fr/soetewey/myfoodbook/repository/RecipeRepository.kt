package fr.soetewey.myfoodbook.repository

import fr.soetewey.myfoodbook.data.Recipe
import kotlinx.coroutines.flow.MutableStateFlow

interface RecipeRepository {
    var listRecipe: MutableStateFlow<List<Recipe>>

    suspend fun addRecipe(recipe: Recipe)

    suspend fun removeRecipe(recipe: Recipe)
}