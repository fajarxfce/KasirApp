package com.fajar.template.core.data

import com.fajar.template.core.data.source.local.entity.CategoryEntity
import com.fajar.template.core.data.source.local.room.CategoryDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryDataSource @Inject constructor(private val categoryDao: CategoryDao) {
    fun addCategories(categoryEntity: CategoryEntity) = flow {
        emit(Resource.Loading())
        try {
            categoryDao.insertCategory(categoryEntity)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun getCategories(): Flow<Resource<List<CategoryEntity>>> {
        return categoryDao.getCategories().map { Resource.Success(it) }
    }

    fun getCategoryById(id: Int) = flow { emit(categoryDao.getCategory(id)) }

    fun updateCategory(categoryEntity: CategoryEntity) = flow {
        emit(Resource.Loading())
        try {
            categoryDao.updateCategory(categoryEntity)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun deleteCategory(id: Int) = flow {
        categoryDao.deleteCategory(id)
        emit(Unit)
    }
}