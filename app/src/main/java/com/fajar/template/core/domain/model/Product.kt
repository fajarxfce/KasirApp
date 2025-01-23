package com.fajar.template.core.domain.model

data class Product(
    val id: Int? = null,
    val name: String,
    val description: String,
    val image: String,
    val price: Double,
    val stock: Int
)
