package com.fajar.template.ui.auth

import androidx.lifecycle.ViewModel
import com.fajar.template.core.domain.model.User
import com.fajar.template.core.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {
    //login
    fun login(email: String, password: String) = authUseCase.login(email, password)

    //register
    fun registerUser(user: User) = authUseCase.registerUser(user)
}