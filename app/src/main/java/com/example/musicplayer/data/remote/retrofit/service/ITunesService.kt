package com.example.musicplayer.data.remote.retrofit.service

import com.example.musicplayer.data.model.FeedResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * iTunes Service
 *
 *
 */
interface ITunesService {

    @POST("topsongs/limit={limit}/xml")
    suspend fun fetchTopSongs(@Path("limit") limit: Int): Response<FeedResponse>
}
