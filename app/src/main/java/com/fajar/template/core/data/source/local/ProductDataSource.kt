package com.fajar.template.core.data.source.local

import android.util.Log
import com.fajar.template.core.data.Resource
import com.fajar.template.core.data.source.local.entity.CategoryEntity
import com.fajar.template.core.data.source.local.entity.ProductCategoryCrossRef
import com.fajar.template.core.data.source.local.entity.ProductEntity
import com.fajar.template.core.data.source.local.room.ProductDao
import com.fajar.template.core.domain.model.Product
import kotlinx.coroutines.flow.Flow
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

    fun updateProduct(product: ProductEntity) = flow {
        productDao.updateProduct(product)
        emit(Unit)
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
    fun addProductCategoryCrossRef(productId: Int, categoryId: Int) : Flow<Resource<Unit>> = flow {
        try {
            val data =  productDao.insertProductCategoryCrossRef(ProductCategoryCrossRef(productId, categoryId))
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    companion object {
        private const val TAG = "ProductDataSource"
    }
}