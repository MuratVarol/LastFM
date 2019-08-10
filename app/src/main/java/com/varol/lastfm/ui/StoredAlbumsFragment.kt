package com.varol.lastfm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.varol.lastfm.R
import com.varol.lastfm.base.BaseFragment
import com.varol.lastfm.databinding.FragmentStoredAlbumsBinding
import com.varol.lastfm.viewmodel.AlbumsVM

class StoredAlbumsFragment :
    BaseFragment<AlbumsVM, FragmentStoredAlbumsBinding>(AlbumsVM::class) {
    override val getLayoutId: Int = R.layout.fragment_stored_albums


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel.getStoredAlbums()

        return binding.root
    }

}