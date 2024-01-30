package fr.soetewey.myfoodbook.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.soetewey.myfoodbook.data.Recipe
import fr.soetewey.myfoodbook.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeRepository : RecipeRepository
) : ViewModel(){
    private var _listRecipe : MutableLiveData<List<Recipe>> = MutableLiveData(emptyList())
    val listRecipe : LiveData<List<Recipe>> = _listRecipe

    private val _nameInput = MutableStateFlow("")
    val nameInput: StateFlow<String> get() = _nameInput

    private var _idInput = MutableStateFlow(0)

    private val _imageInput = MutableStateFlow<Uri?>(null)
    val imageInput : StateFlow<Uri?> = _imageInput

    private val _ingredientsInput = MutableStateFlow<List<String>>(emptyList())
    val ingredientsInput : StateFlow<List<String>> = _ingredientsInput

    private val _stepsInput = MutableStateFlow<List<String>>(emptyList())
    val stepsInput : StateFlow<List<String>> = _stepsInput


    init {
        viewModelScope.launch(Dispatchers.IO) {
            recipeRepository.listRecipe.collect{ list ->
                _listRecipe.postValue(list.sortedBy { recipe -> recipe.name })
            }
        }
    }
    fun onNameChange(name : String) {
        _nameInput.value = name
    }

    fun onImageChange(image : Uri){
        _imageInput.value = image
    }

    fun onIngredientsChange(ingredients : List<String>){
        _ingredientsInput.value = ingredients
    }

    fun onStepsChange(steps : List<String>){
        _stepsInput.value = steps
    }

    suspend fun addRecipe() {
        _idInput.value = Random.nextInt(1, 1000000000)
        //_idInput.value = listRecipe.value?.size ?: 0
        val recipe : Recipe
        if(_imageInput.value != null){
            recipe = Recipe(
                _idInput.value,
                _nameInput.value,
                _imageInput.value!!,
                _ingredientsInput.value,
                _stepsInput.value)
        }
        else {
            recipe = Recipe(
                _idInput.value,
                _nameInput.value,
                null,
                _ingredientsInput.value,
                _stepsInput.value)
        }
        recipeRepository.addRecipe(recipe)
        Log.d("AddRecipe", recipe.toString())
    }

    suspend fun removeRecipe(recipe: Recipe) {
        recipeRepository.removeRecipe(recipe)
    }
}