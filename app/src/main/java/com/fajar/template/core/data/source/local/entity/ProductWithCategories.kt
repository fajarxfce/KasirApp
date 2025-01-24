package com.fajar.template.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ProductWithCategories(
    @Embedded val product: ProductEntity,
    @Relation(
        parentColumn = "productId",
        entityColumn = "categoryId",
        associateBy = Junction(ProductCategoryCrossRef::class)
    )
    val categories: List<Category>
)
