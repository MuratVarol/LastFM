package com.varol.lastfm.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.varol.lastfm.base.BaseVM
import com.varol.lastfm.data.local.model.ArtistModel
import com.varol.lastfm.data.remote.DataHolder
import com.varol.lastfm.usecase.SearchArtistUseCase
import com.varol.lastfm.util.listener.ItemClickListener


class ArtistsVM(
    private val searchArtistUseCase: SearchArtistUseCase
) : BaseVM() {

    val searchText = MutableLiveData<String>()

    val artistSearchList = MutableLiveData<List<ArtistModel>>()

    val itemClickListener = object : ItemClickListener<ArtistModel> {
        override fun onItemClick(view: View, item: ArtistModel, position: Int) {
            Log.i("ArtistModel: ", item.toString())
        }

    }

    fun searchArtist() {

        searchText.value?.let { name ->
            val disposable = searchArtistUseCase
                .searchArtist(name)
                .observeOn(getBackgroundScheduler())
                .subscribeOn(getBackgroundScheduler())
                .subscribe { data ->
                    when (data) {
                        is DataHolder.Success -> {
                            artistSearchList.postValue(data.data.results.artistmatches.artist)
                        }
                        else -> {
                        }
                    }
                }
            disposables.add(disposable)
        }


    }

}