package com.fajar.template.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fajar.template.core.data.source.local.entity.UserEntity

@Database(entities = [
    UserEntity::class,
    ],
    version = 1,
    exportSchema = false)
abstract class CashierDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}