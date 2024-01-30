package fr.soetewey.myfoodbook.view

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import fr.soetewey.myfoodbook.viewmodel.RecipeViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddRecipeScreen(
    navController: NavHostController,
    recipeViewModel: RecipeViewModel = hiltViewModel()
) {

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUri = uri
    }

    var nameInput by remember {
        mutableStateOf("")
    }

    var textFieldsIngredient by remember { mutableStateOf(listOf("")) }
    var textFieldsStep by remember { mutableStateOf(listOf("")) }

    var listIngredients by remember { mutableStateOf(mutableListOf<String>()) }
    var listSteps by remember {
        mutableStateOf(mutableListOf<String>())
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
        content ={
          Column(
              modifier = Modifier
                  .verticalScroll(rememberScrollState())
                  .padding(16.dp)

          ){
              Spacer(modifier = Modifier.height(60.dp))
              TextField(
                  modifier = Modifier
                      .fillMaxWidth(),
                  value = nameInput,
                  onValueChange = {
                      nameInput = it
                      recipeViewModel.onNameChange(it)
                      Log.d("nameInput", it)
                  },
                  label = { Text("Nom de la recette") }
              )
              Spacer(modifier = Modifier.height(10.dp))
              Button(
                  onClick = {
                      launcher.launch("image/*")
                  },
                  modifier = Modifier
                      .fillMaxWidth(),
              ) {
                  Text(
                      text = "Ajouter une photo"
                  )
              }
              if(imageUri != null){
                  val painter = rememberAsyncImagePainter(
                      ImageRequest
                          .Builder(LocalContext.current)
                          .data(data = imageUri)
                          .build()
                  )
                  Spacer(modifier = Modifier.height(5.dp))
                  Image(
                      painter = painter,
                      contentDescription = null
                  )
                  recipeViewModel.onImageChange(image = imageUri!!)
              }
              Spacer(modifier = Modifier.height(10.dp))
              Text("Liste des ingrédients")
              Spacer(modifier = Modifier.height(5.dp))
              textFieldsIngredient.forEachIndexed { index, text->
                  TextField(
                      value = text,
                      onValueChange = { newText ->
                          textFieldsIngredient = textFieldsIngredient.toMutableList().also { it[index] = newText }
                          listIngredients = textFieldsIngredient as MutableList<String>
                      },
                      modifier = Modifier
                          .fillMaxWidth(),
                      label = { Text("Ingrédient ${index+1}") },
                  )
              }
              Button(
                  onClick = {
                      textFieldsIngredient = textFieldsIngredient + ""
                  },
                  modifier = Modifier
                      .align(Alignment.CenterHorizontally)
                      .fillMaxWidth()
              ) {
                  Icon(imageVector = Icons.Default.Add, contentDescription = "Ajouter")
              }
              Spacer(modifier = Modifier.height(10.dp))
              Text("Etapes de préparation")
              Spacer(modifier = Modifier.height(5.dp))
              textFieldsStep.forEachIndexed { index, text->
                  TextField(
                      value = text,
                      onValueChange = { newText ->
                          textFieldsStep = textFieldsStep.toMutableList().also { it[index] = newText }
                          listSteps = textFieldsStep as MutableList<String>
                      },
                      modifier = Modifier
                          .fillMaxWidth(),
                      label = { Text("Etape ${index+1}") },
                  )
              }
              Button(
                  onClick = {
                      textFieldsStep = textFieldsStep + ""
                  },
                  modifier = Modifier
                      .align(Alignment.CenterHorizontally)
                      .fillMaxWidth()
              ) {
                  Icon(imageVector = Icons.Default.Add, contentDescription = "Ajouter")
              }

          }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    recipeViewModel.onStepsChange(listSteps)
                    recipeViewModel.onIngredientsChange(listIngredients)
                    coroutineScope.launch {
                        recipeViewModel.addRecipe()
                    }
                    navController.navigate("recipe_list_screen")
                },
                content = {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "Enregistrer")
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    )

}
