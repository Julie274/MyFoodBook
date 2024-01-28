package fr.soetewey.myfoodbook.repository

import android.util.Log
import fr.soetewey.myfoodbook.data.Recipe
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepositoryImpl @Inject constructor(
    //private val recipeDAO: RecipeDAO,
    //@CoroutineScopeIO private val coroutineScopeIO: CoroutineScope
): RecipeRepository{

    override var listRecipe: List<Recipe> = emptyList()

    override suspend fun addRecipe(recipe: Recipe) {
        listRecipe = listRecipe + recipe
        Log.d("repository", listRecipe.toString())
        //recipeDAO.insertAll(listRecipe)
    }

    override fun removeRecipe(recipe: Recipe){
        listRecipe = listRecipe - recipe
        Log.d("repository", listRecipe.toString())
    }

}