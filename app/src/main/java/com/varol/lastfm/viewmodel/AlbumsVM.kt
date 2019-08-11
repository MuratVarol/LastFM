package com.varol.lastfm.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.varol.lastfm.base.BaseVM
import com.varol.lastfm.data.local.model.AlbumDo
import com.varol.lastfm.data.local.model.AlbumModel
import com.varol.lastfm.data.local.model.AlbumSearchModel
import com.varol.lastfm.data.remote.DataHolder
import com.varol.lastfm.usecase.AlbumDbMapperUseCase
import com.varol.lastfm.usecase.GetStringsUseCase
import com.varol.lastfm.usecase.TopAlbumsUseCase
import com.varol.lastfm.util.binding_adapters.SingleLiveEvent
import com.varol.lastfm.util.listener.ItemClickListener
import io.reactivex.Observable


class AlbumsVM(
    private val getTopAlbumsUseCase: TopAlbumsUseCase,
    private val getStringsUseCase: GetStringsUseCase,
    private val albumDbMapperUseCase: AlbumDbMapperUseCase
) : BaseVM() {


    val albumList = MutableLiveData<List<AlbumModel>>()

    val selectedAlbum = MutableLiveData<AlbumModel>()

    val storedAlbums = MutableLiveData<List<AlbumDo>>()

    val albumDetail = MutableLiveData<AlbumDo>()

    val isLoading = SingleLiveEvent<Boolean>()

    val isEmptyAlbumList = SingleLiveEvent<Boolean>()

    val isSelectedAlbumIsStored = SingleLiveEvent<Boolean>()

    val savedAlbumSelectListener = object : ItemClickListener<AlbumDo> {
        override fun onItemClick(view: View, item: AlbumDo, position: Int) {
            albumDetail.postValue(item)
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
                            val mappedAlbum = albumDbMapperUseCase.mapAlbumWithTracksToAlbumDo(it)
                            isSelectedAlbumStored(mappedAlbum)
                            albumDetail.postValue(mappedAlbum)
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

                isLoading.postValue(false)

                if (albumList.isNotEmpty()) {
                    storedAlbums.postValue(albumList)
                    isEmptyAlbumList.postValue(false)
                } else {
                    isEmptyAlbumList.postValue(true)
                    storedAlbums.postValue(emptyList())
                }
            }, {
                errorMessage.postValue(getStringsUseCase.getFailedToGetAlbumString())
            })

        disposables.add(disposable)
    }

    fun isSelectedAlbumStored(album: AlbumDo) {
        val disposable = getTopAlbumsUseCase.isSelectedAlbumStored(album)
            .subscribeOn(getBackgroundScheduler())
            .observeOn(getBackgroundScheduler())
            .subscribe({ album ->
                album?.let {

                    isSelectedAlbumIsStored.postValue(true)
                } ?: run {
                    isSelectedAlbumIsStored.postValue(false)
                }
            },
                {
                    isSelectedAlbumIsStored.postValue(false)
                })

        disposables.add(disposable)
    }

    fun handleAlbumStorage() {

        if (isSelectedAlbumIsStored.value == true) {
            deleteStoredAlbumFromDatabase()
        } else {
            saveSelectedAlbumToDatabase()
        }

    }


    private fun saveSelectedAlbumToDatabase() {

        albumDetail.value?.let { album ->
            val disposable =
                Observable.just(
                    getTopAlbumsUseCase.saveAlbum(album)
                )
                    .observeOn(getBackgroundScheduler())
                    .subscribeOn(getBackgroundScheduler())
                    .subscribe { rowCount ->
                        getStoredAlbums()
                        rowCount?.let {
                            isEmptyAlbumList.postValue(false)
                            isSelectedAlbumIsStored.postValue(true)
                        }
                    }
            disposables.add(disposable)
        } ?: run {

        }
    }

    private fun deleteStoredAlbumFromDatabase() {

        albumDetail.value?.let { album ->
            val disposable = Observable.just(
                getTopAlbumsUseCase.deleteAlbum(album)
            )
                .subscribeOn(getBackgroundScheduler())
                .observeOn(getBackgroundScheduler())
                .subscribe {
                    isSelectedAlbumIsStored.postValue(false)
                    getStoredAlbums()
                }

            disposables.add(disposable)

        } ?: run {
            isSelectedAlbumIsStored.postValue(false)
        }
    }


}
