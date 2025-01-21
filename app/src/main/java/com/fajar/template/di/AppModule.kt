package com.fajar.template.di

import com.fajar.template.core.domain.usecase.AuthInteractor
import com.fajar.template.core.domain.usecase.AuthUseCase
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
    abstract fun privideExampleUseCase(exampleInteractor: AuthInteractor): AuthUseCase
}