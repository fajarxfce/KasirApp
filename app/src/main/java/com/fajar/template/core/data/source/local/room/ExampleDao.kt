package com.fajar.template.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fajar.template.core.data.source.local.entity.ExampleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExampleDao {

    @Insert
    suspend fun insertExample(example: ExampleEntity)

    @Query("SELECT * FROM example")
    fun getExampleList(): Flow<ExampleEntity>

    @Query("SELECT * FROM example WHERE id = :id")
    fun getExampleById(id: Int): Flow<ExampleEntity>

    @Update
    suspend fun updateExample(example: ExampleEntity)

    @Delete
    suspend fun deleteExample(example: ExampleEntity)

}