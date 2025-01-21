package com.fajar.template.core.di

import android.content.Context
import androidx.room.Room
import com.fajar.template.core.data.source.local.room.ExampleDao
import com.fajar.template.core.data.source.local.room.ExampleDatabase
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
    fun provideDatabase(@ApplicationContext context: Context): ExampleDatabase {
        return Room.databaseBuilder(
            context,
            ExampleDatabase::class.java,
            "example_database"
        )
            .fallbackToDestructiveMigration()
            .build(
        )
    }

    @Provides
    fun provideExampleDao(database: ExampleDatabase): ExampleDao =
        database.exampleDao()
}