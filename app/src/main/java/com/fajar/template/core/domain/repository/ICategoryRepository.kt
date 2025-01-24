package com.fajar.template.core.domain.repository

import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface ICategoryRepository {
    fun addCategory(category: Category) : Flow<Resource<Unit>>
    fun getCategories(): Flow<Resource<List<Category>>>
    fun getCategory(id: Int): Flow<Category>
    fun updateCategory(category: Category) : Flow<Resource<Unit>>
    fun deleteCategory(id: Int) : Flow<Unit>
}