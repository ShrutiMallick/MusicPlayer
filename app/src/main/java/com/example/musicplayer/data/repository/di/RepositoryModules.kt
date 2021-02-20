package com.example.musicplayer.data.repository.di

import com.example.musicplayer.data.repository.ITunesRepository
import com.example.musicplayer.data.repository.ITunesRepositoryImp
import org.koin.dsl.module

/**
 * Configure Repositories
 *
 */
val repositoryModules = module {

    factory<ITunesRepository> {
        ITunesRepositoryImp(get(), get())
    }
}
