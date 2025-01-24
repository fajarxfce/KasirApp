package com.fajar.template.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val productId: Int? = null,
    val name: String,
    val description: String,
    val image: String,
    val stock: Int,
    val sellPrice: Long,
    val purchasePrice: Long,
    val barcode: String,
)
