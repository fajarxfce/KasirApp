package com.fajar.template.core.domain.repository

import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.Category
import com.fajar.template.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    fun getProducts(): Flow<Resource<List<Product>>>
    fun getProduct(id: Int): Flow<Product>
    fun addProduct(product: Product, categories: List<Category>) : Flow<Resource<Long>>
    fun updateProduct(product: Product, categories: List<Category>) : Flow<Resource<Unit>>
    fun deleteProduct(id: Int) : Flow<Resource<Unit>>
}