package fr.soetewey.myfoodbook.data

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class Recipe(
    @PrimaryKey var id: Int,
    val name: String,
    val image: Uri?,
    val ingredients: List<String>,
    val steps: List<String>
)
