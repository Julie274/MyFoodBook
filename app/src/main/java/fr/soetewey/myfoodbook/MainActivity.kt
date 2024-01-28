package fr.soetewey.myfoodbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import fr.soetewey.myfoodbook.nav.NavigationGraph
import fr.soetewey.myfoodbook.ui.theme.MyFoodBookTheme
import fr.soetewey.myfoodbook.view.RecipeListScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationGraph()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyFoodBookTheme {
    }
}