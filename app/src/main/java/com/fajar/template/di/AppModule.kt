package com.fajar.template.di

import com.fajar.template.core.domain.usecase.CategoryInteractor
import com.fajar.template.core.domain.usecase.CategoryUseCase
import com.fajar.template.core.domain.usecase.UserInteractor
import com.fajar.template.core.domain.usecase.UserUseCase
import com.fajar.template.core.domain.usecase.ProductInteractor
import com.fajar.template.core.domain.usecase.ProductUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideUserUseCase(userInteractor: UserInteractor): UserUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideProductUseCase(productInteractor: ProductInteractor): ProductUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideCategoryUseCase(categoryInteractor: CategoryInteractor): CategoryUseCase
}