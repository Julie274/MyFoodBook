package fr.soetewey.myfoodbook.nav

sealed class Screen(val route: String){
    object RecipeListScreen : Screen("recipe_list_screen")
    object AddRecipeScreen : Screen("add_recipe_screen")
    object RecipeScreen : Screen("recipe_screen")
}
