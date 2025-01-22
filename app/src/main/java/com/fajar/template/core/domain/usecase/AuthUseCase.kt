package com.fajar.template.core.domain.usecase

import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    fun login(email: String, password: String): Flow<Resource<User>>
    fun registerUser(user: User): Flow<Unit>
}