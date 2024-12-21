package com.training.ecommerce.domain.repository

import com.training.ecommerce.domain.model.User
import com.training.ecommerce.data.utils.Result

interface AuthRepository {
    suspend fun loginUser(email: String, password: String): Result<User>
    suspend fun registerUser(name: String, email: String, password: String): Result<User>
}