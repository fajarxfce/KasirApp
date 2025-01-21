package com.fajar.template.core.domain.usecase

import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.User
import com.fajar.template.core.domain.repository.IAuthRespository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthInteractor @Inject constructor(private val repository: IAuthRespository): AuthUseCase {
    override fun login(email: String, password: String): Flow<Resource<User>> {
        return repository.login(email, password)
    }
}