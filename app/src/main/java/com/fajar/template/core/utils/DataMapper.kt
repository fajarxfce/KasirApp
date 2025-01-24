package com.fajar.template.core.utils

import com.fajar.template.core.data.source.local.entity.ProductEntity
import com.fajar.template.core.data.source.remote.response.ExampleResponse
import com.fajar.template.core.domain.model.Product

object DataMapper {
    fun productsEntityToDomain(entity: List<ProductEntity>) =
        entity.map {
            Product(
                it.productId,
                it.name,
                it.description,
                it.image,
                it.price,
                it.stock
            )
        }
}