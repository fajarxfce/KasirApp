package com.fajar.template.core.domain.repository

import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    fun getProducts(): Flow<Resource<List<Product>>>
    fun getProduct(id: Int): Flow<Product>
    fun addProduct(product: Product) : Flow<Resource<Unit>>
    fun updateProduct(product: Product) : Flow<Unit>
    fun deleteProduct(id: Int) : Flow<Unit>
}