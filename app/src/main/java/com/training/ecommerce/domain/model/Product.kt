package com.training.ecommerce.domain.model

data class Product(
    val id: Int,
    val productName: String,
    val productImageRes: Int,
    val originalPrice: Double,
    val discountPrice: Double,
    val discountPercent: Int,
    val rating: Int,
)
