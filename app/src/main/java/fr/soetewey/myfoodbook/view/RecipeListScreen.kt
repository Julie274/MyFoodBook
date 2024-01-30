package fr.soetewey.myfoodbook.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fr.soetewey.myfoodbook.ui.theme.FlashyRed
import fr.soetewey.myfoodbook.viewmodel.RecipeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(
    navController: NavHostController,
    recipeViewModel: RecipeViewModel = hiltViewModel()
){
    val recipeList by recipeViewModel.listRecipe.observeAsState(initial = emptyList())
    Box {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .background(FlashyRed),
                    title = {
                        Text(
                            text = "Ma liste de recette"
                        )
                    },
                )
            },
            content = {
                Column {
                    Spacer(modifier = Modifier.height(50.dp))
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth() ,
                        contentPadding = PaddingValues(16.dp) ,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        items(recipeList){recipe ->
                            RecipeCard(navController = navController,recipe)
                        }
                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate("add_recipe_screen")
                    },
                    content = {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Ajouter")
                    }
                )
            },
            floatingActionButtonPosition = FabPosition.End,
        )
    }

}