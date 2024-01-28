package fr.soetewey.myfoodbook.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import fr.soetewey.myfoodbook.data.Recipe
import fr.soetewey.myfoodbook.ui.theme.FlashyRed
import fr.soetewey.myfoodbook.viewmodel.RecipeViewModel

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
                    if (recipe != null) {
                        Text(
                            text = recipe.image
                        )
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
                        if (recipe != null) {
                            items(recipe.ingredients) { ingredient ->
                                Text(text = ingredient.name)
                            }
                        }
                    }
                    Text(
                        text = "Préparation",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    LazyColumn {
                        if (recipe != null) {
                            items(recipe.steps) { step ->
                                Text(text = step)
                            }
                        }
                    }
                }
            },
        )
    }
}

fun List<Recipe>.find(id: Int): Recipe? {
    return find { it.id == id }
}