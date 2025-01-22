package com.fajar.template.core.domain.repository

import com.fajar.template.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    fun getProducts(): Flow<List<Product>>
    fun getProduct(id: Int): Flow<Product>
    fun addProduct(product: Product) : Flow<Unit>
    fun updateProduct(product: Product) : Flow<Unit>
    fun deleteProduct(id: Int) : Flow<Unit>
}