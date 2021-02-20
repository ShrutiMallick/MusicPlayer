package com.example.musicplayer.ui.song

import androidx.lifecycle.LiveData
import com.example.musicplayer.data.repository.ITunesRepository
import com.example.musicplayer.data.room.entity.SongEntity
import com.example.musicplayer.ui.base.BaseViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * ViewModel for SongActivity
 *
 */
class SongViewModel : BaseViewModel(), KoinComponent {

    private val mITunesRepository by inject<ITunesRepository>()
    val songsLiveData: LiveData<List<SongEntity>>

    init {
        // Fetch Top Songs from Database
        songsLiveData = mITunesRepository.getTopSongs()
    }
}
