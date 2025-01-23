package com.fajar.template.core.data.source.local.entity

import androidx.room.Entity

@Entity(tableName = "products", primaryKeys = ["id"])
data class ProductEntity(
    val id: Int?,
    val name: String,
    val description: String,
    val image: String,
    val price: Double,
    val stock: Int
)
