package com.fajar.template.core.data.source.local

import android.util.Log
import com.fajar.template.core.data.Resource
import com.fajar.template.core.data.source.local.entity.CategoryEntity
import com.fajar.template.core.data.source.local.entity.ProductCategoryCrossRef
import com.fajar.template.core.data.source.local.entity.ProductEntity
import com.fajar.template.core.data.source.local.room.ProductDao
import com.fajar.template.core.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductDataSource @Inject constructor(private val productDao: ProductDao) {
    fun getProducts(): Flow<Resource<List<ProductEntity>>> {
        return productDao.getProducts().map { Resource.Success(it) }
    }
    fun getProductById(id: Int) = flow { emit(productDao.getProduct(id)) }

    fun getProductsByCategory(categoryId: Int): Flow<Resource<List<ProductEntity>>> = flow {
        emit(Resource.Loading())
        try {
            val categoryWithProducts = productDao.getCategoriesWithProducts(categoryId).first()
            emit(Resource.Success(categoryWithProducts.products))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun addProduct(product: ProductEntity) : Flow<Resource<Long>> = flow {
        emit(Resource.Loading())
        try {
            val insertedProduct = productDao.insertProduct(product)
            emit(Resource.Success(insertedProduct))
        } catch (e: Exception) {
            Log.d(TAG, "addProduct: ${e.message}")
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun updateProduct(product: ProductEntity) : Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            productDao.updateProduct(product)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun deleteProduct(id: Int) = flow {
        emit(Resource.Loading())
        try {
            productDao.deleteProduct(id)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    suspend fun addProductCategoryCrossRef(productCategoryCrossRef: ProductCategoryCrossRef) {
        return productDao.insertProductCategoryCrossRef(productCategoryCrossRef)
    }

    suspend fun deleteProductCategoryCrossRef(productId: Int) {
        return productDao.deleteProductCategoryCrossRef(productId)
    }

    companion object {
        private const val TAG = "ProductDataSource"
    }
}