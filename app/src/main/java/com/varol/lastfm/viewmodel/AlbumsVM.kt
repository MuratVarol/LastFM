package com.varol.lastfm.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.varol.lastfm.base.BaseVM
import com.varol.lastfm.data.local.model.AlbumDo
import com.varol.lastfm.data.local.model.AlbumModel
import com.varol.lastfm.data.local.model.AlbumSearchModel
import com.varol.lastfm.data.local.model.AlbumWithTracksModel
import com.varol.lastfm.data.remote.DataHolder
import com.varol.lastfm.usecase.GetStringsUseCase
import com.varol.lastfm.usecase.TopAlbumsUseCase
import com.varol.lastfm.util.binding_adapters.SingleLiveEvent
import com.varol.lastfm.util.listener.ItemClickListener


class AlbumsVM(
    private val getTopAlbumsUseCase: TopAlbumsUseCase,
    private val getStringsUseCase: GetStringsUseCase
) : BaseVM() {


    val albumList = MutableLiveData<List<AlbumModel>>()

    val selectedAlbum = MutableLiveData<AlbumModel>()

    val storedAlbums = MutableLiveData<List<AlbumDo>>()

    val albumDetail = MutableLiveData<AlbumWithTracksModel>()

    val isLoading = SingleLiveEvent<Boolean>()

    val isEmptyAlbumList = SingleLiveEvent<Boolean>()

    val savedAlbumSelectListener = object : ItemClickListener<AlbumWithTracksModel> {
        override fun onItemClick(view: View, item: AlbumWithTracksModel, position: Int) {
            Log.i("AlbumWithTracksModel: ", item.toString())
        }
    }

    val albumSelectListener = object : ItemClickListener<AlbumModel> {
        override fun onItemClick(view: View, item: AlbumModel, position: Int) {
            selectedAlbum.postValue(item)
        }
    }

    fun getTopAlbums(artist: String) {
        isLoading.postValue(true)
        val disposable = getTopAlbumsUseCase
            .getTopAlbums(artist)
            .observeOn(getBackgroundScheduler())
            .subscribeOn(getBackgroundScheduler())
            .subscribe({ data ->
                isLoading.postValue(false)
                when (data) {
                    is DataHolder.Success -> {
                        albumList.postValue(data.data.topalbums.albums)
                    }
                    else -> {
                        errorMessage.postValue(getStringsUseCase.getFailedToGetAlbumString())
                    }
                }
            }
                , {
                    errorMessage.postValue(getStringsUseCase.getFailedToGetAlbumString())
                })
        disposables.add(disposable)
    }


    fun getAlbumDetail(albumSearch: AlbumSearchModel) {

        isLoading.postValue(true)

        val disposable = getTopAlbumsUseCase
            .getAlbumDetail(albumSearch)
            .observeOn(getBackgroundScheduler())
            .subscribeOn(getBackgroundScheduler())
            .subscribe({ data ->

                isLoading.postValue(false)

                when (data) {
                    is DataHolder.Success -> {
                        val albumList = data.data.album
                        albumList?.let {
                            albumDetail.postValue(data.data.album)
                        } ?: run {
                            errorMessage.postValue(getStringsUseCase.getFailedToGetAlbumString())
                        }
                    }
                    else -> {
                        errorMessage.postValue(getStringsUseCase.getFailedToGetAlbumString())
                    }
                }
            }
                , {
                    errorMessage.postValue(getStringsUseCase.getFailedToGetAlbumString())
                })
        disposables.add(disposable)
    }

    fun getStoredAlbums() {
        isLoading.postValue(true)

        val disposable = getTopAlbumsUseCase
            .getStoredAlbums()
            .observeOn(getBackgroundScheduler())
            .subscribeOn(getBackgroundScheduler())
            .subscribe({ albumList ->
                if (albumList.isNotEmpty()) {
                    storedAlbums.postValue(albumList)
                } else {
                    isEmptyAlbumList.postValue(true)
                }
            }, {
                errorMessage.postValue(getStringsUseCase.getFailedToGetAlbumString())
            })

        disposables.add(disposable)
    }


}
