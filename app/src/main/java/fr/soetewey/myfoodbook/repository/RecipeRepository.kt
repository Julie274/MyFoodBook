package fr.soetewey.myfoodbook.repository

import fr.soetewey.myfoodbook.data.Recipe

interface RecipeRepository {
    val listRecipe : List<Recipe>
}