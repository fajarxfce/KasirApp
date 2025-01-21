package com.fajar.template.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "example")
class ExampleEntity(
    @PrimaryKey
    val id: Int?,
    val name: String?,
    val description: String?
)