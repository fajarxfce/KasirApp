package com.fajar.template.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.User
import com.fajar.template.core.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {
    //login
    fun login(email: String, password: String, onResult: (Resource<User>) -> Unit) {
        viewModelScope.launch {
            authUseCase.login(email, password).collect {
                onResult(it)
            }
        }
    }
    //register
    fun registerUser(user: User, onResult: () -> Unit) {
        viewModelScope.launch {
            authUseCase.registerUser(user).collect {
                onResult()
            }
        }
    }
}