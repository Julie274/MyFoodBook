package fr.soetewey.myfoodbook.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.soetewey.myfoodbook.data.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDAO {
    /*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Recipe>)

    @Query("SELECT * FROM recipe_table")
    fun getRecipeListFlow(): Flow<List<Recipe>>

     */
}