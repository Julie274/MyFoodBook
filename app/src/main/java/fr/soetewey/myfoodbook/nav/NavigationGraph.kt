package fr.soetewey.myfoodbook.nav

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.soetewey.myfoodbook.view.AddRecipeScreen
import fr.soetewey.myfoodbook.view.RecipeListScreen
import fr.soetewey.myfoodbook.view.RecipeScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "profile"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "recipe_list_screen"
    ) {
        composable("recipe_list_screen") {
            RecipeListScreen(navController)
        }
        composable("recipe_card") {
            RecipeListScreen(navController)
        }
        composable("add_recipe_screen") {
            AddRecipeScreen(navController)
        }
        composable("recipe_screen/{recipeId}") {
            RecipeScreen(navController)
        }
    }
}
/*
@Composable
@SuppressLint("UnusedTransitionTargetStateParameter")
fun NavigationGraph(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = Screen.RecipeListScreen.route
    ) {
        composable(Screen.RecipeListScreen.route) {
            RecipeListScreen(navController)
        }
        composable(Screen.AddRecipeScreen.route) {
            AddRecipeScreen(navController)
        }
        composable(Screen.RecipeScreen.route) {
            RecipeScreen(navController)
        }
    }
}*/