package com.varol.lastfm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.varol.lastfm.R
import com.varol.lastfm.base.BaseFragment
import com.varol.lastfm.databinding.FragmentSearchBinding
import com.varol.lastfm.viewmodel.ArtistsVM
import observe

class SearchArtistFragment :
    BaseFragment<ArtistsVM, FragmentSearchBinding>(ArtistsVM::class) {
    override val getLayoutId: Int = R.layout.fragment_search


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        subscribeSelectedArtist()

        return binding.root
    }

    private fun subscribeSelectedArtist() {
        viewModel.selectedArtist.observe(this) { artistModel ->
            artistModel?.let { artist ->
                val topAlbumDetailFragment = TopAlbumsFragment.newInstance(artist)
                loadFragment(R.id.container_main, topAlbumDetailFragment, fragmentManager, true)
            }
        }
    }

}