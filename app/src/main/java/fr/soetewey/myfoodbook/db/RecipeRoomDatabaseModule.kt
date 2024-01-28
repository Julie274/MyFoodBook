package fr.soetewey.myfoodbook.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RecipeRoomDatabaseModule {
/*
    @Provides
    fun provideRecipeDao(recipeRoomDatabase: RecipeRoomDatabase): RecipeDAO{
        return recipeRoomDatabase.recipeDao()
    }

    @Provides
    @Singleton
    fun provideRecipeRoomDatabase(@ApplicationContext appContext: Context): RecipeRoomDatabase{
        return Room.databaseBuilder(
            appContext.applicationContext,
            RecipeRoomDatabase::class.java,
            "recipe_database"
        ).build()
    }*/
}