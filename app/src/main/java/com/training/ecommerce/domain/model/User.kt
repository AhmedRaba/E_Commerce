package com.training.ecommerce.domain.model

data class User(
    val id: String,
    val email: String,
    val name: String? = null,
    val password: String? = null
)
