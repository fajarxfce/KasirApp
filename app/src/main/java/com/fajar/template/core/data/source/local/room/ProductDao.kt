package com.fajar.template.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.fajar.template.core.data.source.local.entity.CategoryEntity
import com.fajar.template.core.data.source.local.entity.CategoryWithProducts
import com.fajar.template.core.data.source.local.entity.ProductCategoryCrossRef
import com.fajar.template.core.data.source.local.entity.ProductEntity
import com.fajar.template.core.data.source.local.entity.ProductWithCategories
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Transaction
    @Query("SELECT * FROM product")
    fun getProductsWithCategories(): List<ProductWithCategories>

    @Transaction
    @Query("SELECT * FROM categories")
    fun getCategoriesWithProducts(): List<CategoryWithProducts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductCategoryCrossRef(productCategoryCrossRef: ProductCategoryCrossRef)

    @Insert
    suspend fun insertProduct(product: ProductEntity)

    @Update
    suspend fun updateProduct(product: ProductEntity)

    @Query("SELECT * FROM product")
    fun getProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE productId = :id")
    suspend fun getProduct(id: Int): ProductEntity

    @Query("DELETE FROM product WHERE productId = :id")
    suspend fun deleteProduct(id: Int)
}