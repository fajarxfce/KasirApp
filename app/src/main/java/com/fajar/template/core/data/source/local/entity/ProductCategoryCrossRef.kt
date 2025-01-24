package com.fajar.template.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["product_id", "category_id"],
    tableName = "product_category_cross_ref")
data class ProductCategoryCrossRef (
    @ColumnInfo(name = "product_id")
    val productId: Int,
    @ColumnInfo(name = "category_id")
    val categoryId: Int
)