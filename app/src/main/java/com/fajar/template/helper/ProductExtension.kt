package com.fajar.template.helper

import com.fajar.template.core.data.source.local.entity.ProductEntity
import com.fajar.template.core.domain.model.Product

fun ProductEntity.toProduct() = Product(
    id = productId,
    name = name,
    description = description,
    image = image,
    sellPrice = sellPrice,
    purchasePrice = purchasePrice,
    stock = stock,
    barcode = barcode
)

fun Product.toEntity() = ProductEntity(
    productId = id,
    name = name,
    description = description,
    image = image,
    sellPrice = sellPrice,
    purchasePrice = purchasePrice,
    stock = stock,
    barcode = barcode
)