package com.training.ecommerce.domain.usecase

import com.training.ecommerce.domain.model.User
import com.training.ecommerce.data.utils.Result
import com.training.ecommerce.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(name: String, email: String, password: String): Result<User> {
        return repository.registerUser(name, email, password)
    }
}