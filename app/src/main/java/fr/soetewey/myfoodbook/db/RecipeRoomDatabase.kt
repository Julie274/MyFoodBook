package fr.soetewey.myfoodbook.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.soetewey.myfoodbook.data.Recipe

@TypeConverters(Converters::class)
@Database(
    entities = [Recipe::class],
    version = 1,
    exportSchema = false
)
abstract class RecipeRoomDatabase : RoomDatabase(){
    abstract fun recipeDao(): RecipeDAO
}