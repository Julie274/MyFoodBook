package fr.soetewey.myfoodbook.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.soetewey.myfoodbook.data.Ingredient
import fr.soetewey.myfoodbook.data.Recipe
import fr.soetewey.myfoodbook.data.Unit
import fr.soetewey.myfoodbook.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeRepository : RecipeRepository
) : ViewModel(){
    private var _listRecipe : MutableLiveData<List<Recipe>> = MutableLiveData(recipeRepository.listRecipe)
    val listRecipe : LiveData<List<Recipe>> = _listRecipe

    private val _nameInput = MutableStateFlow("")
    val nameInput: StateFlow<String> get() = _nameInput

    private val _idInput = MutableStateFlow("")
    val idInput: StateFlow<String> get() = _idInput

    private val ingredientsInput : List<Ingredient> = listOf(Ingredient("Farine",2,Unit.g))
    private val stepsInput : List<String> = listOf("")
    private val imageInput : String = ""

    fun onNameChange(name : String){
        _nameInput.value = name
    }

    fun onIdChange(id : String){
        _idInput.value = id
    }

    suspend fun addRecipe() {
        val recipe = Recipe(_idInput.value.toInt(),_nameInput.value,imageInput,ingredientsInput,stepsInput)
        recipeRepository.addRecipe(recipe)
        Log.d("AddRecipe", recipe.toString())
    }

    fun removeRecipe(recipe: Recipe) {
        recipeRepository.removeRecipe(recipe)
    }
}