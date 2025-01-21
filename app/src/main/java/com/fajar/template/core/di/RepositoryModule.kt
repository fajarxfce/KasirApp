package com.fajar.template.core.di

import androidx.room.Insert
import com.fajar.template.core.data.ExampleRepository
import com.fajar.template.core.domain.repository.IExampleRespository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    abstract fun provideRepository(
        exampleRepository: ExampleRepository
    ): IExampleRespository
}