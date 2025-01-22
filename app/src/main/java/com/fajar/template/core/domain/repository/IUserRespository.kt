package com.fajar.template.core.domain.repository

import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRespository {
    fun login(email: String, password: String): Flow<Resource<User>>
    fun registerUser(user: User): Flow<Unit>
}