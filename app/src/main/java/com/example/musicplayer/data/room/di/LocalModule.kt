package com.example.musicplayer.data.room.di

import com.example.musicplayer.data.room.AppDatabase
import org.koin.dsl.module

/**
 * Database dependencies
 *
 *
 */
val localModule = module {

    single { AppDatabase.getInstance(get()) }

    single { (get() as AppDatabase).songDao() }
}
