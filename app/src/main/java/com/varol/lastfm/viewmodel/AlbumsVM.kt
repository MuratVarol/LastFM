package com.varol.lastfm.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.varol.lastfm.base.BaseVM
import com.varol.lastfm.data.local.model.AlbumModel
import com.varol.lastfm.data.local.model.AlbumSearchModel
import com.varol.lastfm.data.local.model.AlbumWithTracksModel
import com.varol.lastfm.data.remote.DataHolder
import com.varol.lastfm.usecase.TopAlbumsUseCase
import com.varol.lastfm.util.listener.ItemClickListener


class AlbumsVM(
    private val getTopAlbumsUseCase: TopAlbumsUseCase
) : BaseVM() {


    val albumList = MutableLiveData<List<AlbumModel>>()

    val albumDetail = MutableLiveData<AlbumWithTracksModel>()
    val selectedAlbum = MutableLiveData<AlbumModel>()

    val itemClickListener = object : ItemClickListener<AlbumModel> {
        override fun onItemClick(view: View, item: AlbumModel, position: Int) {
            Log.i("AlbumModel: ", item.toString())
        }
    }

    val albumSelectListener = object : ItemClickListener<AlbumModel> {
        override fun onItemClick(view: View, item: AlbumModel, position: Int) {
            selectedAlbum.postValue(item)
        }
    }

    fun getTopAlbums(artist: String) {

        val disposable = getTopAlbumsUseCase
            .getTopAlbums(artist)
            .observeOn(getBackgroundScheduler())
            .subscribeOn(getBackgroundScheduler())
            .subscribe { data ->
                when (data) {
                    is DataHolder.Success -> {
                        albumList.postValue(data.data.topalbums.albums)
                    }
                    else -> {
                    }
                }
            }
        disposables.add(disposable)
    }


    fun getAlbumDetail(albumSearch: AlbumSearchModel) {

        val disposable = getTopAlbumsUseCase
            .getAlbumDetail(albumSearch)
            .observeOn(getBackgroundScheduler())
            .subscribeOn(getBackgroundScheduler())
            .subscribe { data ->
                when (data) {
                    is DataHolder.Success -> {
                        albumDetail.postValue(data.data.album)
                    }
                    else -> {
                    }
                }
            }
        disposables.add(disposable)
    }


}
