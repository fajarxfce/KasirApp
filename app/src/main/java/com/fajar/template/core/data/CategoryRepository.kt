package com.fajar.template.core.data

import com.fajar.template.core.data.source.local.entity.CategoryEntity
import com.fajar.template.core.domain.model.Category
import com.fajar.template.core.domain.repository.ICategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(private val categoryDataSource: CategoryDataSource) :
    ICategoryRepository {
    override fun addCategory(category: Category): Flow<Resource<Unit>> =
        categoryDataSource.addCategories(CategoryEntity(name = category.name))

    override fun getCategories(): Flow<Resource<List<Category>>> {
        return categoryDataSource.getCategories().map { resource ->
            when (resource) {
                is Resource.Loading -> Resource.Loading()
                is Resource.Success -> Resource.Success(resource.data?.map {
                    Category(
                        it.categoryId,
                        it.name
                    )
                } ?: emptyList())
                is Resource.Error -> Resource.Error("Error: ${resource.message}")
            }
        }
    }

    override fun getCategory(id: Int): Flow<Category> {
        return categoryDataSource.getCategoryById(id).map {
            Category(
                it.categoryId,
                it.name
            )
        }
    }

    override fun updateCategory(category: Category): Flow<Resource<Unit>> =
        categoryDataSource.updateCategory(CategoryEntity(category.id, category.name))

    override fun deleteCategory(id: Int): Flow<Unit> {
        return categoryDataSource.deleteCategory(id)
    }

}