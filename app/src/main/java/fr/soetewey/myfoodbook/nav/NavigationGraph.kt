package fr.soetewey.myfoodbook.nav

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.soetewey.myfoodbook.view.AddRecipeScreen
import fr.soetewey.myfoodbook.view.RecipeListScreen

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
    }
}