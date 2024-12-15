package com.training.ecommerce.data.auth.datasource

import com.training.ecommerce.data.model.User
import com.training.ecommerce.data.utils.Result


interface AuthDataSource {

    suspend fun login(
        email: String,
        password: String,
    ): Result<User>

    suspend fun register(
        name: String,
        email: String,
        password: String
    ): Result<User>
}