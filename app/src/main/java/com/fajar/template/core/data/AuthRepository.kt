package com.fajar.template.core.data

import com.fajar.template.core.data.source.local.UserDataSource
import com.fajar.template.core.domain.model.User
import com.fajar.template.core.domain.repository.IAuthRespository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val userDataSource: UserDataSource
): IAuthRespository {
    override fun login(email: String, password: String): Flow<Resource<User>> {
        return userDataSource.login(email, password).map { entity ->
            if (entity != null) {
                Resource.Success(User(entity.id, entity.name, entity.email, entity.password))
            } else {
                Resource.Error("User not found")
            }

        }
    }
}