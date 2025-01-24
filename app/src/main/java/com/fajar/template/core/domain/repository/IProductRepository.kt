package com.fajar.template.core.domain.repository

import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.Category
import com.fajar.template.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    fun getProducts(): Flow<Resource<List<Product>>>
    fun getProduct(id: Int): Flow<Product>
    fun addProduct(product: Product) : Flow<Resource<Unit>>
    fun updateProduct(product: Product) : Flow<Unit>
    fun deleteProduct(id: Int) : Flow<Unit>

    //category
    fun addCategory(category: Category) : Flow<Resource<Unit>>
    fun getCategories(): Flow<Resource<List<Category>>>
    fun getCategory(id: Int): Flow<Category>
    fun updateCategory(category: Category) : Flow<Resource<Unit>>
    fun deleteCategory(id: Int) : Flow<Unit>
}