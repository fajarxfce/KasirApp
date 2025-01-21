package com.fajar.template.core.data.source.local

import com.fajar.template.core.data.source.local.entity.ExampleEntity
import com.fajar.template.core.data.source.local.room.ExampleDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val exampleDao: ExampleDao) {

        fun getExampleList() = exampleDao.getExampleList()

        fun getExampleById(id: Int) = exampleDao.getExampleById(id)

        suspend fun insertExample(example: ExampleEntity) = exampleDao.insertExample(example)

        suspend fun updateExample(example: ExampleEntity) = exampleDao.updateExample(example)

        suspend fun deleteExample(example: ExampleEntity) = exampleDao.deleteExample(example)
}