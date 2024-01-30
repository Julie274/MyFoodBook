package fr.soetewey.myfoodbook.repository

import android.util.Log
import fr.soetewey.myfoodbook.data.Recipe
import fr.soetewey.myfoodbook.db.CoroutineScopeIO
import fr.soetewey.myfoodbook.db.RecipeDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepositoryImpl @Inject constructor(
    private val recipeDAO: RecipeDAO,
    @CoroutineScopeIO private val coroutineScopeIO: CoroutineScope
): RecipeRepository{

    override var listRecipe:  MutableStateFlow<List<Recipe>> = MutableStateFlow(emptyList())//List<Recipe> = emptyList()
    init {
        coroutineScopeIO.launch {
            recipeDAO.getRecipeListFlow().collect{ list ->
                listRecipe.emit(list)
            }
        }
    }

    override suspend fun addRecipe(recipe: Recipe) {
        Log.d("repository", listRecipe.toString())
        recipeDAO.insert(recipe)
    }

    override suspend fun removeRecipe(recipe: Recipe){
        Log.d("repository", listRecipe.toString())
        recipeDAO.delete(recipe)
    }

}