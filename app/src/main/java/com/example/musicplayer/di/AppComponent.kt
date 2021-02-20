package com.example.musicplayer.di

import com.example.musicplayer.BuildConfig
import com.example.musicplayer.data.remote.di.remoteModules
import com.example.musicplayer.data.remote.di.remoteServiceModules
import com.example.musicplayer.data.repository.di.repositoryModules
import com.example.musicplayer.data.room.di.localModule

/**
 * Combine all dependencies
 *
 *
 */
val appComponent = listOf(
    remoteModules(BuildConfig.API_BASE_URL, BuildConfig.DEBUG),
    remoteServiceModules,
    repositoryModules,
    localModule
)
