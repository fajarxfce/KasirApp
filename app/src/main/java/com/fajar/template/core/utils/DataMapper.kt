package com.fajar.template.core.utils

import com.fajar.template.core.data.source.local.entity.ProductEntity
import com.fajar.template.core.data.source.remote.response.ExampleResponse
import com.fajar.template.core.domain.model.Product

object DataMapper {
    fun productsEntityToDomain(entity: List<ProductEntity>) =
        entity.map {
            Product(
                id = it.productId,
                name = it.name,
                description = it.description,
                image = it.image,
                sellPrice = it.sellPrice,
                purchasePrice = it.purchasePrice,
                stock = it.stock,
                barcode = it.barcode
            )
        }
}