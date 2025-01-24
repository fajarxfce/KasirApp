package com.fajar.template.core.domain.model

data class Product(
    val id: Int? = null,
    val name: String,
    val description: String,
    val image: String,
    val stock: Int,
    val sellPrice: Long,
    val purchasePrice: Long,
    val barcode: String,
)
