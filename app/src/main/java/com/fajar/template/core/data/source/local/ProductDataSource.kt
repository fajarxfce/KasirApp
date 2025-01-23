package com.fajar.template.core.data.source.local

import com.fajar.template.core.data.Resource
import com.fajar.template.core.data.source.local.entity.ProductEntity
import com.fajar.template.core.data.source.local.room.ProductDao
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductDataSource @Inject constructor(private val productDao: ProductDao) {
    fun getProducts() = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(productDao.getProducts()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
    fun getProductById(id: Int) = flow { emit(productDao.getProduct(id)) }
    fun addProduct(product: ProductEntity) = flow {
        emit(Resource.Loading())
        try {
            productDao.insertProduct(product)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun updateProduct(product: ProductEntity) = flow {
        productDao.updateProduct(product)
        emit(Unit)
    }

    fun deleteProduct(id: Int) = flow {
        productDao.deleteProduct(id)
        emit(Unit)
    }

}