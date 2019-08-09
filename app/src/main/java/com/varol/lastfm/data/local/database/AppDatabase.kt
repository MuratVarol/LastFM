package com.varol.lastfm.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.varol.lastfm.BuildConfig
import com.varol.lastfm.data.local.database.dao.AlbumsDao
import com.varol.lastfm.data.local.database.type_convertor.RoomConvertor
import com.varol.lastfm.data.local.model.AlbumDo

@Database(
    entities = [AlbumDo::class], version = BuildConfig.VERSION_CODE, exportSchema = false
)
@TypeConverters(RoomConvertor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun albumsDao(): AlbumsDao

}