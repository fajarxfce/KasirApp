package com.fajar.template.core.domain.usecase

import com.fajar.template.core.data.CategoryRepository
import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.Category
import com.fajar.template.core.domain.repository.ICategoryRepository
import com.fajar.template.core.domain.repository.IProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryInteractor @Inject constructor(private val repository: ICategoryRepository) : CategoryUseCase{
    override fun addCategory(category: Category): Flow<Resource<Unit>> {
        return repository.addCategory(category)
    }

    override fun getCategories(): Flow<Resource<List<Category>>> {
        return repository.getCategories()
    }

    override fun getCategory(id: Int): Flow<Category> {
        return repository.getCategory(id)
    }

    override fun updateCategory(category: Category): Flow<Resource<Unit>> {
        return repository.updateCategory(category)
    }

    override fun deleteCategory(id: Int): Flow<Unit> {
        return repository.deleteCategory(id)
    }

}