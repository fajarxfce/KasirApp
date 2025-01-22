package com.fajar.template.core.di

import com.fajar.template.core.data.UserRepository
import com.fajar.template.core.domain.repository.IUserRespository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    abstract fun provideRepository(
        userRepository: UserRepository
    ): IUserRespository
}