package com.fajar.template.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey
    val id: Int?,
    val name: String?,
    val email: String?,
    val password: String?
)