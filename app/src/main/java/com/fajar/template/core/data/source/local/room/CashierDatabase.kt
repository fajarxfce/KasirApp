package com.fajar.template.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fajar.template.core.data.source.local.entity.CategoryEntity
import com.fajar.template.core.data.source.local.entity.ProductCategoryCrossRef
import com.fajar.template.core.data.source.local.entity.ProductEntity
import com.fajar.template.core.data.source.local.entity.UserEntity

@Database(
    entities = [
                    UserEntity::class,
                    ProductEntity::class,
                    CategoryEntity::class,
                    ProductCategoryCrossRef::class
               ],
    version = 1,
    exportSchema = false)
abstract class CashierDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
}