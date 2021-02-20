package com.example.musicplayer.ui.song

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.musicplayer.R
import com.example.musicplayer.data.room.entity.SongEntity

/**
 * Songs Listing Screen
 *
 */
object SongBindingAdapter {

    @JvmStatic
    @BindingAdapter("srcCoverImage")
    fun bindSrcCoverImage(imageView: ImageView, url: String?) {
        imageView.load(url) {
            val radius = imageView.resources.getDimension(R.dimen.cover_small_corner_radius)
            transformations(RoundedCornersTransformation(radius))
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["adapter"])
    fun bindAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>?) {
        recyclerView.run {
            this.setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    @JvmStatic
    @BindingAdapter("adapterSongItems")
    fun bindAdapterSongItems(view: RecyclerView, pokemonList: List<SongEntity>?) {
        pokemonList?.let { itemList ->
            (view.adapter as? SongListAdapter)?.refresh(itemList)
        }
    }
}
