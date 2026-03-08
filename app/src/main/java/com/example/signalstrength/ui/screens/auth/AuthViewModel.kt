package com.example.signalstrength.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalstrength.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _isLoginMode = MutableStateFlow(true)
    val isLoginMode: StateFlow<Boolean> = _isLoginMode.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _loginSuccess = MutableSharedFlow<Unit>()
    val loginSuccess = _loginSuccess.asSharedFlow()

    fun onEmailChange(value: String) { _email.value = value }
    fun onPasswordChange(value: String) { _password.value = value }
    fun toggleMode() { _isLoginMode.value = !_isLoginMode.value; _error.value = null }

    fun onSubmit() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val result = if (_isLoginMode.value) {
                authRepository.login(_email.value.trim(), _password.value)
            } else {
                authRepository.register(_email.value.trim(), _password.value)
            }

            result
                .onSuccess { _loginSuccess.emit(Unit) }
                .onFailure { _error.value = it.message ?: "Authentication failed" }

            _isLoading.value = false
        }
    }
}
