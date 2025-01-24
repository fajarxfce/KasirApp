package com.fajar.template.core.data.source.local

import com.fajar.template.core.data.Resource
import com.fajar.template.core.data.source.local.entity.CategoryEntity
import com.fajar.template.core.data.source.local.entity.ProductCategoryCrossRef
import com.fajar.template.core.data.source.local.entity.ProductEntity
import com.fajar.template.core.data.source.local.room.ProductDao
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
    fun addProductCategoryCrossRef(productId: Int, categoryId: Int) = flow {
        productDao.insertProductCategoryCrossRef(ProductCategoryCrossRef(productId, categoryId))
        emit(Unit)
    }

    fun addCategories(categoryEntity: CategoryEntity) = flow {
        emit(Resource.Loading())
        try {
            productDao.insertCategory(categoryEntity)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun getCategories(): Flow<Resource<List<CategoryEntity>>> {
        return productDao.getCategories().map { Resource.Success(it) }
    }

    fun getCategoryById(id: Int) = flow { emit(productDao.getCategory(id)) }

    fun updateCategory(categoryEntity: CategoryEntity) = flow {
        emit(Resource.Loading())
        try {
            productDao.updateCategory(categoryEntity)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun deleteCategory(id: Int) = flow {
        productDao.deleteCategory(id)
        emit(Unit)
    }

}