package com.fajar.template.core.domain.usecase

import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.Category
import com.fajar.template.core.domain.model.Product
import com.fajar.template.core.domain.repository.IProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductInteractor @Inject constructor(private val repository: IProductRepository) :
    ProductUseCase {
    override fun getProducts(): Flow<Resource<List<Product>>> {
        return repository.getProducts()
    }

    override fun getProduct(id: Int): Flow<Product> {
        return repository.getProduct(id)
    }

    override fun addProduct(product: Product, categories: List<Category>): Flow<Resource<Long>> {
        return repository.addProduct(product, categories)
    }

    override fun updateProduct(product: Product, categories: List<Category>): Flow<Resource<Unit>> {
        return repository.updateProduct(product, categories)
    }

    override fun deleteProduct(id: Int): Flow<Resource<Unit>> {
        return repository.deleteProduct(id)
    }

    override fun insertProductCategoryCrossRef(
        productId: Int,
        categoryId: Int
    ): Flow<Resource<Unit>> {
        return repository.insertProductCategoryCrossRef(productId, categoryId)
    }
}