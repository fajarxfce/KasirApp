package com.fajar.template.core.domain.usecase

import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.User
import com.fajar.template.core.domain.repository.IUserRespository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInteractor @Inject constructor(private val repository: IUserRespository): UserUseCase {
    override fun login(email: String, password: String): Flow<Resource<User>> {
        return repository.login(email, password)
    }

    override fun registerUser(user: User): Flow<Unit> {
        return repository.registerUser(user)
    }
}