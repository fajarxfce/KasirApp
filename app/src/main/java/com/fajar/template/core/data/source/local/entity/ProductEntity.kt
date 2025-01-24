package com.fajar.template.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val productId: Int?,
    val name: String,
    val description: String,
    val image: String,
    val price: Double,
    val stock: Int
)
