package com.varol.lastfm.di

import android.content.Context
import androidx.room.Room
import com.varol.lastfm.APP_DATABASE_NAME
import com.varol.lastfm.data.local.database.AppDatabase
import com.varol.lastfm.data.local.database.dao.AlbumsDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

/**
 * databaseModule definitions for dependency injection
 *
 * This part will consist of injection Database based objects
 */

val databaseModule = module {
    single { createAppDatabase(androidContext()) }
    single { createAlbumsDao(get()) }
}

fun createAppDatabase(context: Context): AppDatabase {
    return Room
        .databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            APP_DATABASE_NAME
        )
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
}

fun createAlbumsDao(appDatabase: AppDatabase): AlbumsDao {
    return appDatabase.albumsDao()
}