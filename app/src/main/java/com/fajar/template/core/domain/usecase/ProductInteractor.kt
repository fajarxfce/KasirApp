package com.fajar.template.core.domain.usecase

import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.Product
import com.fajar.template.core.domain.repository.IProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductInteractor @Inject constructor(private val repository: IProductRepository) :
    ProductUseCase {
    override fun getProducts(): Flow<List<Product>> {
        return repository.getProducts()
    }

    override fun getProduct(id: Int): Flow<Product> {
        return repository.getProduct(id)
    }

    override fun addProduct(product: Product): Flow<Resource<Unit>> {
        return repository.addProduct(product)
    }

    override fun updateProduct(product: Product): Flow<Unit> {
        return repository.updateProduct(product)
    }

    override fun deleteProduct(id: Int): Flow<Unit> {
        return repository.deleteProduct(id)
    }


}