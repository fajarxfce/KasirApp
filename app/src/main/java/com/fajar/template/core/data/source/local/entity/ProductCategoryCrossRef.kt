package com.fajar.template.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["productId", "categoryId"],
    tableName = "product_category_cross_ref")
data class ProductCategoryCrossRef (
    val productId: Int,
    val categoryId: Int
)