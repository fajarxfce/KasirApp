package com.fajar.template.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ProductWithCategories(
    @Embedded
    val product: ProductEntity,
    @Relation(
        parentColumn = "productId", // Use the correct column name from the Product entity
        entityColumn = "categoryId", // Use the correct column name from the ProductCategoryCrossRef entity
        associateBy = Junction(ProductCategoryCrossRef::class)
    )
    val categories: List<CategoryEntity>
)
