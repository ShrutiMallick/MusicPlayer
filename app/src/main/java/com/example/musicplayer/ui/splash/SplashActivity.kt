package com.example.musicplayer.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.musicplayer.R
import com.example.musicplayer.databinding.ActivitySplashBinding
import com.example.musicplayer.ui.base.BaseActivity
import com.example.musicplayer.ui.song.SongActivity

/**
 * Splash Screen
 *
 *
 */
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override fun getLayout() = R.layout.activity_splash

    override fun getViewModel(): SplashViewModel {
        return ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Apply Binding
        mViewDataBinding.viewModel = mViewModel

        initObserver()

        // Perform Initialization
        mViewModel.preProcessing()
    }

    /**
     * Listen to ViewModel changes
     */
    private fun initObserver() {
        // Observe Sync Complete
        mViewModel.syncLiveData.observe(
            this,
            { _ ->
                startSongActivity()
            }
        )

        // Observe Error
        mViewModel.errorLiveData.observe(
            this,
            { error ->
                showErrorMessage(error)
            }
        )
    }

    private fun showErrorMessage(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }

    /**
     * Navigate to Song Listing Screen
     */
    private fun startSongActivity() {
        // Start Song Activity
        val intent = Intent(this, SongActivity::class.java)
        startActivity(intent)

        // Finish Splash Activity. It should not open on back press
        finish()
    }
}
