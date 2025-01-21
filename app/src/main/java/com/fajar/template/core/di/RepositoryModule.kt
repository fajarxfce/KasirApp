package com.fajar.template.core.di

import com.fajar.template.core.data.AuthRepository
import com.fajar.template.core.domain.repository.IAuthRespository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    abstract fun provideRepository(
        authRepository: AuthRepository
    ): IAuthRespository
}