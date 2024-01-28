package fr.soetewey.myfoodbook.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.soetewey.myfoodbook.data.Ingredient
import fr.soetewey.myfoodbook.data.Recipe
import fr.soetewey.myfoodbook.data.Unit
import fr.soetewey.myfoodbook.ui.theme.MyFoodBookTheme

@Composable
fun RecipeCard(recipe: Recipe){

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.height(120.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .size(width = 100.dp, height = 100.dp)
                    .align(Alignment.CenterVertically)
            ) {
                // Contenu de la boîte (peut être vide si vous ne voulez qu'un rectangle coloré)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = recipe.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.width(10.dp))
                LazyRow{
                    items(recipe.ingredients) {ingredient ->
                        Text(
                            text = ingredient.name,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f),
                            fontSize = 12.sp
                        )
                        Text(
                            ", "
                        )
                    }
                }
            }

        }
    }
}
@Preview( showBackground = true )
@Composable
fun RecipeCardPreview () {
    val ingredients = listOf(
        Ingredient("Farine",50, Unit.g),
        Ingredient("Sucre",50, Unit.g),
        Ingredient("Oeuf",50, Unit.g),
        Ingredient("Beurre",50, Unit.g),
        Ingredient("Chocolat",50, Unit.g),
        Ingredient("Levure",50, Unit.g),
    )
    val step : List<String> = emptyList()
    MyFoodBookTheme {
        RecipeCard (Recipe("Cookie","", ingredients, step))
    }
}