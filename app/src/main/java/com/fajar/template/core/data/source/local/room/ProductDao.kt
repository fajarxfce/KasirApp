package com.fajar.template.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.fajar.template.core.data.source.local.entity.CategoryWithProducts
import com.fajar.template.core.data.source.local.entity.ProductCategoryCrossRef
import com.fajar.template.core.data.source.local.entity.ProductEntity
import com.fajar.template.core.data.source.local.entity.ProductWithCategories
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Transaction
    @Query("SELECT * FROM categories WHERE categoryId = :categoryId")
    fun getProductsByCategoryId(categoryId: Int): Flow<CategoryWithProducts>

    @Transaction
    @Query("SELECT * FROM product WHERE productId = :productId")
    fun getCategoriesByProductId(productId: Int): Flow<ProductWithCategories>

    @Insert
    suspend fun insertProductCategoryCrossRef(productCategoryCrossRef: ProductCategoryCrossRef)

    @Query("DELETE FROM product_category_cross_ref WHERE productId = :productId")
    suspend fun deleteProductCategoryCrossRef(productId: Int)

    @Insert
    suspend fun insertProduct(product: ProductEntity) : Long

    @Update
    suspend fun updateProduct(product: ProductEntity)

    @Query("SELECT * FROM product")
    fun getProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE productId = :id")
    suspend fun getProduct(id: Int): ProductEntity

    @Query("DELETE FROM product WHERE productId = :id")
    suspend fun deleteProduct(id: Int)
}