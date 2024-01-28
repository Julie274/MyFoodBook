package fr.soetewey.myfoodbook.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RecipeRepositoryModule {
    @Binds
    abstract fun bindRecipeRepository(recipeRepositoryImpl: RecipeRepositoryDummyImpl):
            RecipeRepository
}