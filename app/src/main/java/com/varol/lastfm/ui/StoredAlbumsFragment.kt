package com.varol.lastfm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.varol.lastfm.R
import com.varol.lastfm.base.BaseFragment
import com.varol.lastfm.databinding.FragmentStoredAlbumsBinding
import com.varol.lastfm.extension.informToast
import com.varol.lastfm.viewmodel.AlbumsVM
import observe
import observeNonNull

class StoredAlbumsFragment :
    BaseFragment<AlbumsVM, FragmentStoredAlbumsBinding>(AlbumsVM::class) {
    override val getLayoutId: Int = R.layout.fragment_stored_albums


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        subscribeSelectedAlbum()

        subscribeErrorMessage()

        // safe call (?) could give warning as unnecessary
        // experienced NPE before
        binding.ivSearch?.setOnClickListener {
            loadFragment(R.id.container_main, SearchArtistFragment(), fragmentManager, true)
        }

        return binding.root
    }

    private fun subscribeSelectedAlbum() {
        viewModel.albumDetail.observe(this) { albumDetail ->
            albumDetail?.let { album ->
                val albumDetailFragment = AlbumDetailFragment.newInstance(album)
                loadFragment(R.id.container_main, albumDetailFragment, fragmentManager, true)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getStoredAlbums()
    }


    private fun subscribeErrorMessage() {
        viewModel.errorMessage.observeNonNull(this) { errorMessage ->
            informToast(errorMessage)
        }
    }


}