package com.varol.lastfm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.varol.lastfm.R
import com.varol.lastfm.base.BaseFragment
import com.varol.lastfm.data.local.model.AlbumSearchModel
import com.varol.lastfm.databinding.FragmentAlbumDetailBinding
import com.varol.lastfm.viewmodel.AlbumsVM

class AlbumDetailFragment :
    BaseFragment<AlbumsVM, FragmentAlbumDetailBinding>(AlbumsVM::class) {
    override val getLayoutId: Int = R.layout.fragment_album_detail


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel.getAlbumDetail(AlbumSearchModel("cher", "believe"))

        return binding.root
    }

}