package com.fajar.template.di

import com.fajar.template.core.domain.usecase.ExampleInteractor
import com.fajar.template.core.domain.usecase.ExampleUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)

abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun privideExampleUseCase(exampleInteractor: ExampleInteractor): ExampleUseCase
}