package com.fajar.template.core.data.source.local

import com.fajar.template.core.data.source.local.entity.UserEntity
import com.fajar.template.core.data.source.local.room.UserDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataSource @Inject constructor(private val authDao: UserDao) {
    fun login(email: String, password: String): Flow<UserEntity?> =
        flow { emit(authDao.login(email, password)) }


    fun registerUser(user: UserEntity): Flow<Unit> = flow {
        authDao.insertUser(user)
        emit(Unit)
    }
}