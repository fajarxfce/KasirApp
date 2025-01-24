package com.fajar.template.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.fajar.template.core.data.source.local.entity.CategoryWithProducts
import com.fajar.template.core.data.source.local.entity.ProductEntity
import com.fajar.template.core.data.source.local.entity.ProductWithCategories
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Transaction
    @Query("SELECT * FROM products")
    fun getProductsWithCategories(): List<ProductWithCategories>

    @Transaction
    @Query("SELECT * FROM categories")
    fun getCategoriesWithProducts(): List<CategoryWithProducts>

    @Insert
    suspend fun insertProduct(product: ProductEntity)

    @Update
    suspend fun updateProduct(product: ProductEntity)

    @Query("SELECT * FROM products")
    fun getProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProduct(id: Int): ProductEntity

    @Query("DELETE FROM products WHERE id = :id")
    suspend fun deleteProduct(id: Int)
}