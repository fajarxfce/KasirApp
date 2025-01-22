package com.fajar.template.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fajar.template.core.data.source.local.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    fun login(email: String, password: String): UserEntity

    @Insert
    suspend fun insertUser(user: UserEntity)
}