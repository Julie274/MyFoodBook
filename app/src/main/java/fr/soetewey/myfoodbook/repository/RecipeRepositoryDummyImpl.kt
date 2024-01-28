package fr.soetewey.myfoodbook.repository

import fr.soetewey.myfoodbook.data.Ingredient
import fr.soetewey.myfoodbook.data.Recipe
import fr.soetewey.myfoodbook.data.Unit
import javax.inject.Inject

class RecipeRepositoryDummyImpl @Inject constructor(): RecipeRepository{

    override val listRecipe: List<Recipe>
        get() = listOf(
            Recipe(
                "Cookie",
                "",
                listOf(
                    Ingredient("",4, Unit.g),
                    Ingredient("",4, Unit.g)
                ),
                listOf("","")
            ),
            Recipe(
                "Cookie",
                "",
                listOf(
                    Ingredient("Farine",4, Unit.g),
                    Ingredient("Oeuf",4, Unit.g),
                    Ingredient("Farine",4, Unit.g),
                    Ingredient("Oeuf",4, Unit.g),
                    Ingredient("Farine",4, Unit.g),
                    Ingredient("Oeuf",4, Unit.g),
                    Ingredient("Farine",4, Unit.g),
                    Ingredient("Oeuf",4, Unit.g)
                ),
                listOf("","")
            )
        )
}