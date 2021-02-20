package com.example.musicplayer.ui.song

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.musicplayer.R
import com.example.musicplayer.data.room.entity.SongEntity
import com.example.musicplayer.databinding.ActivitySongBinding
import com.example.musicplayer.ui.base.BaseActivity
import com.example.musicplayer.ui.song_detail.SongDetailActivity

/**
 * Songs Listing Screen
 *
 *
 */
class SongActivity : BaseActivity<ActivitySongBinding, SongViewModel>() {

    private val mAdapter: SongListAdapter by lazy {
        SongListAdapter()
    }

    override fun getLayout() = R.layout.activity_song

    override fun getViewModel(): SongViewModel {
        return ViewModelProvider(this).get(SongViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Apply Binding
        mViewDataBinding.apply {
            viewModel = mViewModel
            adapter = mAdapter
        }

        initToolbar()
        initUI()
    }

    /**
     * Set Toolbar
     */
    private fun initToolbar() {
        val toolbar = mViewDataBinding.appbarLayout.toolbar
        setSupportActionBar(toolbar)
    }

    /**
     * Setup UI
     */
    private fun initUI() {
        mAdapter.setItemClickListener { song ->
            startSongDetailActivity(song)
        }
    }

    /**
     * Navigate to Song Detail Screen
     */
    private fun startSongDetailActivity(song: SongEntity) {
        // Start Song Detail Activity
        val intent = Intent(this, SongDetailActivity::class.java)
        intent.putExtra(SongDetailActivity.EXTRA_SONG_LIST, ArrayList(mAdapter.itemList))
        intent.putExtra(SongDetailActivity.EXTRA_SONG, song)
        startActivity(intent)
    }
}
