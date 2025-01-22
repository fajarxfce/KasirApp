package com.fajar.template.core.domain.usecase

import com.fajar.template.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductUseCase {
    fun getProducts(): Flow<List<Product>>
    fun getProduct(id: Int): Flow<Product>
    fun addProduct(product: Product) : Flow<Unit>
    fun updateProduct(product: Product) : Flow<Unit>
    fun deleteProduct(id: Int) : Flow<Unit>
}