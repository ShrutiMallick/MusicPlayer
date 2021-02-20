package com.example.musicplayer.data.repository

import androidx.lifecycle.LiveData
import com.example.musicplayer.data.model.FeedEntry
import com.example.musicplayer.data.remote.NetworkResponse
import com.example.musicplayer.data.remote.retrofit.service.ITunesService
import com.example.musicplayer.data.room.dao.SongDao
import com.example.musicplayer.data.room.entity.SongEntity
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * iTunes Repository
 *
 */
interface ITunesRepository {

    fun getTopSongs(): LiveData<List<SongEntity>>

    suspend fun fetchTopSongs(limit: Int = 20): NetworkResponse<List<SongEntity>>
}

internal class ITunesRepositoryImp(
    private val service: ITunesService,
    private val songDao: SongDao,
) : BaseRepository(), ITunesRepository {

    override fun getTopSongs() = songDao.getAll()

    override suspend fun fetchTopSongs(limit: Int): NetworkResponse<List<SongEntity>> {
        val songList = songDao.getAll().value
        if (!songList.isNullOrEmpty()) {
            return NetworkResponse.Success(songList)
        }

        // Fetch from remote
        val response = safeApiCall(
            call = { service.fetchTopSongs(limit) }
        )

        val list = mutableListOf<SongEntity>()
        if (response is NetworkResponse.Success) {
            // Save Data to SQLite
            response.data.feed
                ?.let {
                    parseSongEntity(it)
                }
                ?.also {
                    list.addAll(it)
                    songDao.insert(it)
                }
        } else if (response is NetworkResponse.Error) {
            return response
        }

        return NetworkResponse.Success(list)
    }

    private fun parseSongEntity(feedList: List<FeedEntry>): List<SongEntity> {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
        return feedList.map {
            val image = it.images?.maxByOrNull { it.height ?: 0 }
            val link = it.links?.firstOrNull { it.type == "audio/x-m4a" }

            SongEntity(
                id = it.id ?: 0L,
                name = it.name ?: "",
                title = it.title ?: "",

                link = link?.url ?: "",
                duration = link?.duration ?: 0,

                artist = it.artist?.value ?: "",

                cover = image?.value,

                rights = it.rights ?: "",

                updateAt = sdf.parse(it.updateAt)
            )
        }
    }
}
