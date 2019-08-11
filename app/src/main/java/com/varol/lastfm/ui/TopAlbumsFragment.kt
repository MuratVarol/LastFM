package com.varol.lastfm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.varol.lastfm.R
import com.varol.lastfm.base.BaseFragment
import com.varol.lastfm.data.local.model.AlbumSearchModel
import com.varol.lastfm.databinding.FragmentTopAlbumsBinding
import com.varol.lastfm.extension.ifLet
import com.varol.lastfm.extension.informToast
import com.varol.lastfm.viewmodel.AlbumsVM
import observeNonNull


const val KEY_ALBUM_NAME = "key.info.album"


class TopAlbumsFragment :
    BaseFragment<AlbumsVM, FragmentTopAlbumsBinding>(AlbumsVM::class) {

    override val getLayoutId: Int = R.layout.fragment_top_albums

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        getAlbumName()?.let { albumName ->
            viewModel.getTopAlbums(albumName)
        }

        subscribeSelectedAlbum()

        subscribeErrorMessage()

        return binding.root
    }


    companion object {

        fun newInstance(albumName: String): TopAlbumsFragment {
            val args = Bundle()
            args.putString(KEY_ALBUM_NAME, albumName)
            return TopAlbumsFragment().apply {
                arguments = args
            }
        }

    }

    private fun getAlbumName(): String? =
        arguments?.getString(KEY_ALBUM_NAME, null)

    private fun subscribeSelectedAlbum() {
        viewModel.selectedAlbum.observeNonNull(this) { selectedAlbum ->

            ifLet(selectedAlbum.artist.name, selectedAlbum.name) { (artist, name) ->
                val albumSearchModel = AlbumSearchModel(artist, name)
                val albumDetailFragment = AlbumDetailFragment.newInstance(albumSearchModel)
                loadFragment(R.id.container_main, albumDetailFragment, fragmentManager, true)
            }

        }
    }

    private fun subscribeErrorMessage() {
        viewModel.errorMessage.observeNonNull(this) { errorMessage ->
            informToast(errorMessage)
        }
    }

}