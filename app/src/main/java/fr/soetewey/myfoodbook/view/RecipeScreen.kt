package fr.soetewey.myfoodbook.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import fr.soetewey.myfoodbook.data.Recipe
import fr.soetewey.myfoodbook.ui.theme.FlashyRed
import fr.soetewey.myfoodbook.viewmodel.RecipeViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(
    navController: NavHostController,
    recipeViewModel: RecipeViewModel = hiltViewModel()
){
    val recipeList by recipeViewModel.listRecipe.observeAsState(initial = emptyList())

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments
    val recipeId = arguments?.getString("recipeId")

    val coroutineScope = rememberCoroutineScope()

    val recipe = recipeId?.let { recipeList.find(it.toInt()) }
    Box{

        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .background(FlashyRed),
                    title = {
                        if (recipe != null) {
                            Text(
                                text = recipe.name,
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }
                    },
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Log.d("Etat recipe", recipe.toString())
                    if (recipe != null) {
                        if (recipe.image != null) {
                            val painter = rememberAsyncImagePainter(
                                ImageRequest
                                    .Builder(LocalContext.current)
                                    .data(data = recipe.image)
                                    .build()
                            )
                            Image(
                                painter = painter,
                                contentDescription = null)
                        }
                        Text(
                            text= "Ingrédients",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                        LazyColumn(
                            modifier = Modifier
                                .padding(16.dp)
                        ){
                            items(recipe.ingredients) { ingredient ->
                                Text(text = ingredient)
                            }
                        }
                        Text(
                            text = "Préparation",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                        LazyColumn {
                            items(recipe.steps) { step ->
                                Text(text = step)
                            }
                        }
                    }

                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        if (recipe != null) {
                            coroutineScope.launch {
                                recipeViewModel.removeRecipe(recipe)
                            }
                        }
                        navController.navigate("recipe_list_screen")
                    },
                    content = {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Supprimer")
                    }
                )
            },
            floatingActionButtonPosition = FabPosition.End,
        )
    }
}

fun List<Recipe>.find(id: Int): Recipe? {
    return find { it.id == id }
}