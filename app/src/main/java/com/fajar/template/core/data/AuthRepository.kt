package com.fajar.template.core.data

import android.util.Log
import com.fajar.template.core.data.source.local.UserDataSource
import com.fajar.template.core.data.source.local.entity.UserEntity
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
        Log.d(TAG, "login: anjing")
        return userDataSource.login(email, password).map { entity ->
            Log.d(TAG, "login: $entity")
            if (entity != null) {
                Log.d(TAG, "login: ${entity.name}")
                Resource.Success(User(entity.id, entity.name, entity.email, entity.password))
            } else {
                Log.d(TAG, "login: User not found")
                Resource.Error("User not found")
            }

        }
    }

    override fun registerUser(user: User): Flow<Unit> {
        val userEntity = UserEntity(user.id, user.name, user.email, user.password)
        return userDataSource.registerUser(userEntity)
    }

    companion object {
        private const val TAG = "AuthRepository"
    }
}