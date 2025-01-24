package com.fajar.template.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fajar.template.core.data.source.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    //crud for categories
    @Insert
    suspend fun insertCategory(categoryEntity: CategoryEntity)

    @Update
    suspend fun updateCategory(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM categories")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM categories WHERE categoryId = :id")
    fun getCategory(id: Int): CategoryEntity

    @Query("DELETE FROM categories WHERE categoryId = :id")
    suspend fun deleteCategory(id: Int)
}