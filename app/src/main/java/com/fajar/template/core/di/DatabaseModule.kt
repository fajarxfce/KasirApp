package com.fajar.template.core.di

import android.content.Context
import androidx.room.Room
import com.fajar.template.core.data.source.local.room.CashierDatabase
import com.fajar.template.core.data.source.local.room.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CashierDatabase {
        return Room.databaseBuilder(
            context,
            CashierDatabase::class.java,
            "cashier_db"
        )
            .fallbackToDestructiveMigration()
            .build(
        )
    }

    @Provides
    fun provideUserDao(database: CashierDatabase): UserDao =
        database.userDao()
}