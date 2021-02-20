package com.example.musicplayer.data.remote.di

import com.example.musicplayer.data.remote.retrofit.service.ITunesService
import org.koin.dsl.module
import retrofit2.Retrofit


val remoteServiceModules = module {

    factory { get<Retrofit>().create(ITunesService::class.java) }
}
