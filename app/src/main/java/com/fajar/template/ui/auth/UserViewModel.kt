package com.fajar.template.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.User
import com.fajar.template.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {
    //login
    fun login(email: String, password: String, onResult: (Resource<User>) -> Unit) {
        viewModelScope.launch {
            userUseCase.login(email, password).collect {
                onResult(it)
            }
        }
    }
    //register
    fun registerUser(user: User, onResult: () -> Unit) {
        viewModelScope.launch {
            userUseCase.registerUser(user).collect {
                onResult()
            }
        }
    }
}