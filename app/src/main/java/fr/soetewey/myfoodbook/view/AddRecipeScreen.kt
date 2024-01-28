package fr.soetewey.myfoodbook.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fr.soetewey.myfoodbook.viewmodel.RecipeViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeScreen(
    navController: NavHostController,
    recipeViewModel: RecipeViewModel = hiltViewModel()
) {

    var nameInput by remember {
        mutableStateOf("")
    }

    var idInput by remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Nouvelle recette"
                    )
                },
            )
        },
        content = {
          Column {
              Spacer(modifier = Modifier.height(50.dp))
              TextField(
                  value = nameInput,
                  onValueChange = {
                      nameInput = it
                      recipeViewModel.onNameChange(it)
                  },
                  label = { Text("Nom de la recette") }
              )
              TextField(
                  value = idInput,
                  onValueChange = {
                      idInput = it
                      recipeViewModel.onIdChange(it)
                  },
                  label = { Text("ID") }
              )
              Button(
                  onClick = {
                      coroutineScope.launch {
                          recipeViewModel.addRecipe()
                          Log.d("coroutine","On est dans la coroutine")
                      }
                      navController.navigate("recipe_list_screen")
                  },
                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(8.dp)
              ) {
                  Text("Enregistrer la recette")
              }
          }
        },
    )

}