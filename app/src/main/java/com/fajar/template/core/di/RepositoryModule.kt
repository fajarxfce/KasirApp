package com.fajar.template.core.di

import com.fajar.template.core.data.CategoryRepository
import com.fajar.template.core.data.ProductRepository
import com.fajar.template.core.data.UserRepository
import com.fajar.template.core.domain.repository.ICategoryRepository
import com.fajar.template.core.domain.repository.IProductRepository
import com.fajar.template.core.domain.repository.IUserRespository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    abstract fun provideUserRepository(
        userRepository: UserRepository
    ): IUserRespository

    @Binds
    abstract fun provideProductRepository(
        productRepository: ProductRepository
    ): IProductRepository

    @Binds
    abstract fun provideCategoryRepository(
        categoryRepository: CategoryRepository
    ): ICategoryRepository
}