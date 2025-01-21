package com.fajar.template.core.domain.repository

import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IAuthRespository {
    fun login(email: String, password: String): Flow<Resource<User>>
}