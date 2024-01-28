package fr.soetewey.myfoodbook.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class Recipe(
    @PrimaryKey var id: Int,
    val name: String,
    val image: String,
    val ingredients: List<Ingredient>,
    val steps: List<String>
)
