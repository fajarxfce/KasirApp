package com.fajar.template.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fajar.template.core.data.source.local.entity.ExampleEntity

@Database(entities = [
    ExampleEntity::class,
                     ],
    version = 1,
    exportSchema = false)
abstract class ExampleDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}