package com.training.ecommerce.data.auth.repository

import com.training.ecommerce.data.auth.datasource.AuthDataSource
import com.training.ecommerce.domain.model.User
import com.training.ecommerce.data.utils.Result

import com.training.ecommerce.domain.repository.AuthRepository

class AuthRepositoryImpl(private val dataSource: AuthDataSource) : AuthRepository {

    override suspend fun loginUser(email: String, password: String): Result<User> {
        return dataSource.login(email, password)
    }

    override suspend fun registerUser(name: String, email: String, password: String): Result<User> {
        return dataSource.register(name, email, password)
    }

}