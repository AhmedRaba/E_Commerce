package com.training.ecommerce.data.model

data class User(
    val id: String,
    val email: String,
    val name: String? = null,
    val password: String? = null
)
