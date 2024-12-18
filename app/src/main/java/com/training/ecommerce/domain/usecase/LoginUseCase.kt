package com.training.ecommerce.domain.usecase

import com.training.ecommerce.data.model.User
import com.training.ecommerce.data.utils.Result
import com.training.ecommerce.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthRepository)  {
    suspend operator fun invoke(email:String,password:String): Result<User>{
        return repository.loginUser(email,password)
    }
}