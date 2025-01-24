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
                        it.productId,
                        it.name,
                        it.description,
                        it.image,
                        it.price,
                        it.stock
                    )
                } ?: emptyList())
                is Resource.Error -> Resource.Error("Error: ${resource.message}")
            }
        }
    }

    override fun getProduct(id: Int): Flow<Product> {
        return productDataSource.getProductById(id).map { productEntity ->
            Product(
                productEntity.productId,
                productEntity.name,
                productEntity.description,
                productEntity.image,
                productEntity.price,
                productEntity.stock
            )
        }
    }

    override fun addProduct(product: Product): Flow<Resource<Unit>> {
        Log.d(TAG, "addProduct: ${product.name}")
        val productEntity = ProductEntity(
            product.id,
            product.name,
            product.description,
            product.image,
            product.price,
            product.stock
        )
        return productDataSource.addProduct(productEntity)
    }

    override fun updateProduct(product: Product): Flow<Unit> {
        val productEntity = ProductEntity(
            product.id,
            product.name,
            product.description,
            product.image,
            product.price,
            product.stock
        )
        return productDataSource.updateProduct(productEntity)
    }

    override fun deleteProduct(id: Int): Flow<Unit> {
        return productDataSource.deleteProduct(id)
    }

    override fun addCategory(category: Category): Flow<Resource<Unit>> =
        productDataSource.addCategories(CategoryEntity(name = category.name))

    override fun getCategories(): Flow<Resource<List<Category>>> {
        return productDataSource.getCategories().map { resource ->
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
        return productDataSource.getCategoryById(id).map {
            Category(
                it.categoryId,
                it.name
            )
        }
    }

    override fun updateCategory(category: Category): Flow<Resource<Unit>> =
        productDataSource.updateCategory(CategoryEntity(category.id, category.name))

    override fun deleteCategory(id: Int): Flow<Unit> {
        return productDataSource.deleteCategory(id)
    }


    companion object {
        private const val TAG = "ProductRepository"
    }
}