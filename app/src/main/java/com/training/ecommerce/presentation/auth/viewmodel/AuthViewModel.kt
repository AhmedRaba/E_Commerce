package com.training.ecommerce.presentation.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.ecommerce.data.model.User
import com.training.ecommerce.data.utils.Result
import com.training.ecommerce.domain.usecase.LoginUseCase
import com.training.ecommerce.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {

    private val _authLoginState = MutableStateFlow<Result<User>?>(null)
    val authLoginState: StateFlow<Result<User>?> = _authLoginState

    private val _authRegisterState = MutableStateFlow<Result<User>?>(null)
    val authRegisterState: StateFlow<Result<User>?> = _authRegisterState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authLoginState.value = loginUseCase(email, password)
        }
    }

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _authRegisterState.value = registerUseCase(name, email, password)
        }
    }


    fun resetAuthState() {
        _authLoginState.value = null
        _authRegisterState.value = null
    }


}