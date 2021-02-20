package com.example.musicplayer.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.musicplayer.data.room.converter.DateConverter
import com.example.musicplayer.data.room.dao.SongDao
import com.example.musicplayer.data.room.entity.SongEntity

/**
 * Room Database Instance
 *

 */
@Database(version = 1, entities = [SongEntity::class])
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "itunes_player.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun songDao(): SongDao
}
