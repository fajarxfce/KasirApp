package com.fajar.template.core.data

import android.util.Log
import com.fajar.template.core.data.source.local.ProductDataSource
import com.fajar.template.core.data.source.local.entity.CategoryEntity
import com.fajar.template.core.data.source.local.entity.ProductEntity
import com.fajar.template.core.domain.model.Category
import com.fajar.template.core.domain.model.Product
import com.fajar.template.core.domain.repository.IProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val productDataSource: ProductDataSource
) : IProductRepository {
    override fun getProducts(): Flow<Resource<List<Product>>> {
        return productDataSource.getProducts().map { resource ->
            when (resource) {
                is Resource.Loading -> Resource.Loading()
                is Resource.Success -> Resource.Success(resource.data?.map {
                    Product(
                        id = it.productId,
                        name = it.name,
                        description = it.description,
                        image = it.image,
                        sellPrice = it.sellPrice,
                        purchasePrice = it.purchasePrice,
                        stock = it.stock,
                        barcode = it.barcode
                    )
                } ?: emptyList())
                is Resource.Error -> Resource.Error("Error: ${resource.message}")
            }
        }
    }

    override fun getProduct(id: Int): Flow<Product> {
        return productDataSource.getProductById(id).map { productEntity ->
            Product(
                id = productEntity.productId,
                name = productEntity.name,
                description = productEntity.description,
                image = productEntity.image,
                sellPrice = productEntity.sellPrice,
                purchasePrice = productEntity.purchasePrice,
                stock = productEntity.stock,
                barcode = productEntity.barcode
            )
        }
    }

    override fun addProduct(product: Product, categories: List<Category>): Flow<Resource<Long>> = productDataSource.addProduct(product.toEntity()).map { resource ->
        when (resource) {
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> {
                val productId = resource.data!!
                categories.forEach { category ->
                    Log.d(TAG, "addProduct: $productId, ${category.id}")
                    productDataSource.addProductCategoryCrossRef(resource.data.toInt(), category.id!!).map { result->
                        when (result) {
                            is Resource.Loading -> Resource.Loading()
                            is Resource.Success -> Resource.Success(productId)
                            is Resource.Error -> Resource.Error("Error: ${result.message}")
                        }

                    }
                }
                Resource.Success(productId)
            }
            is Resource.Error -> Resource.Error("Error: ${resource.message}")
        }
    }

    override fun updateProduct(product: Product): Flow<Unit> {
        val productEntity = ProductEntity(
            productId = product.id,
            name = product.name,
            description = product.description,
            image = product.image,
            sellPrice = product.sellPrice,
            purchasePrice = product.purchasePrice,
            stock = product.stock,
            barcode = product.barcode
        )
        return productDataSource.updateProduct(productEntity)
    }

    override fun deleteProduct(id: Int): Flow<Resource<Unit>> {
        return productDataSource.deleteProduct(id)
    }
        companion object {
        private const val TAG = "ProductRepository"
    }

    fun Product.toEntity(): ProductEntity {
        return ProductEntity(
            productId = this.id,
            name = this.name,
            description = this.description,
            image = this.image,
            sellPrice = this.sellPrice,
            purchasePrice = this.purchasePrice,
            stock = this.stock,
            barcode = this.barcode
        )
    }
}