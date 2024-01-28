package fr.soetewey.myfoodbook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.soetewey.myfoodbook.data.Recipe
import fr.soetewey.myfoodbook.repository.RecipeRepository
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    recipeRepository : RecipeRepository
) : ViewModel(){
    private val _listRecipe : MutableLiveData<List<Recipe>> = MutableLiveData(recipeRepository.listRecipe)
    val listRecipe : LiveData<List<Recipe>> = _listRecipe
}