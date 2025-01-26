package com.fajar.template.core.data

import android.util.Log
import com.fajar.template.core.data.source.local.ProductDataSource
import com.fajar.template.core.domain.model.Category
import com.fajar.template.core.domain.model.Product
import com.fajar.template.core.domain.repository.IProductRepository
import com.fajar.template.helper.toEntity
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

    override fun addProduct(product: Product, categories: List<Category>): Flow<Resource<Long>> =
        productDataSource.addProduct(product.toEntity()).map { resource ->
            when (resource) {
                is Resource.Loading -> Resource.Loading()
                is Resource.Success -> {
                    val productId = resource.data!!
                    categories.forEach { category ->
                        Log.d(TAG, "addProduct: $productId, ${category.id}")
                        productDataSource.addProductCategoryCrossRef(
                            resource.data.toInt(),
                            category.id!!
                        ).map { result ->
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

    override fun updateProduct(product: Product, categories: List<Category>): Flow<Resource<Unit>> {

        return productDataSource.updateProduct(product.toEntity()).map {
            categories.forEach { category ->
                Log.d(
                    TAG,
                    "updateProduct: product id : ${product.id}, category id : ${category.id}"
                )
                productDataSource.addProductCategoryCrossRef(product.id!!, category.id!!)
                    .map { result ->
                        when (result) {
                            is Resource.Loading -> Resource.Loading()
                            is Resource.Success -> Resource.Success(Unit)
                            is Resource.Error -> Resource.Error("Error: ${result.message}")
                        }
                    }
            }
            Resource.Success(Unit)
        }
    }

    override fun deleteProduct(id: Int): Flow<Resource<Unit>> {
        return productDataSource.deleteProduct(id)
    }

    override fun insertProductCategoryCrossRef(
        productId: Int,
        categoryId: Int
    ): Flow<Resource<Unit>> {
        return productDataSource.addProductCategoryCrossRef(productId, categoryId)
    }
    companion object {
        private const val TAG = "ProductRepository"
    }
}