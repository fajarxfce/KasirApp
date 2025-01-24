package com.fajar.template.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CategoryWithProducts(
    @Embedded val categoryEntity: CategoryEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "productId",
        associateBy = Junction(ProductCategoryCrossRef::class)
    )
    val products: List<ProductEntity>
)
