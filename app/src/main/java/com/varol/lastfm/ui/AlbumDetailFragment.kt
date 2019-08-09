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


const val KEY_ALBUM_INFO = "key.info.album"

class AlbumDetailFragment :
    BaseFragment<AlbumsVM, FragmentAlbumDetailBinding>(AlbumsVM::class) {
    override val getLayoutId: Int = R.layout.fragment_album_detail


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        getSelectedAlbum()?.let { album ->
            viewModel.getAlbumDetail(AlbumSearchModel(album.artist, album.albumName))
        }

        return binding.root
    }

    companion object {

        fun newInstance(albumSearchModel: AlbumSearchModel): AlbumDetailFragment {
            val args = Bundle()
            args.putParcelable(KEY_ALBUM_INFO, albumSearchModel)
            return AlbumDetailFragment().apply {
                arguments = args
            }
        }

    }


    private fun getSelectedAlbum(): AlbumSearchModel? =
        arguments?.getParcelable(KEY_ALBUM_INFO)

}