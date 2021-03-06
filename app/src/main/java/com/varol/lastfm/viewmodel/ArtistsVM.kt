package com.varol.lastfm.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.varol.lastfm.base.BaseVM
import com.varol.lastfm.data.local.model.ArtistModel
import com.varol.lastfm.data.remote.DataHolder
import com.varol.lastfm.usecase.GetStringsUseCase
import com.varol.lastfm.usecase.SearchArtistUseCase
import com.varol.lastfm.util.binding_adapters.SingleLiveEvent
import com.varol.lastfm.util.listener.ItemClickListener
import plusAssign


class ArtistsVM(
    private val searchArtistUseCase: SearchArtistUseCase,
    private val getStringsUseCase: GetStringsUseCase
) : BaseVM() {

    val searchText = MutableLiveData<String>()

    val artistSearchList = MutableLiveData<MutableList<ArtistModel>>()

    val selectedArtist = SingleLiveEvent<String>()

    val isLoading = SingleLiveEvent<Boolean>()


    val itemClickListener = object : ItemClickListener<ArtistModel> {
        override fun onItemClick(view: View, item: ArtistModel, position: Int) {
            selectedArtist.postValue(item.name)
        }

    }

    /**
     * Search method without parameter for databinding
     */
    fun searchArtist() {
        searchArtist(INIT_PAGE_INDEX)
    }

    fun searchArtist(pageIndex: Int = INIT_PAGE_INDEX) {

        searchText.value?.let { name ->

            isLoading.postValue(true)

            val disposable = searchArtistUseCase
                .searchArtist(name, pageIndex)
                .observeOn(getMainThreadScheduler())
                .subscribeOn(getMainThreadScheduler())
                .subscribe { data ->

                    when (data) {
                        is DataHolder.Success -> {

                            val artistsList = data.data.results?.artistmatches?.artist
                            artistsList?.let {

                                when {
                                    pageIndex == INIT_PAGE_INDEX -> if (!artistsList.isNullOrEmpty()) {
                                        artistSearchList.postValue(artistsList.toMutableList())
                                    } else {
                                        errorMessage.postValue(getStringsUseCase.getNoArtistFoundString())
                                    }
                                    pageIndex > INIT_PAGE_INDEX -> //keep below line, could be useful later
                                        //                                    if (artistSearchList.value?.last()?.mbid != artistsList.last().mbid)
                                        artistSearchList += artistsList.toMutableList()
                                }
                            } ?: run {
                                errorMessage.postValue(getStringsUseCase.getSearchFailedString())
                                return@subscribe
                            }
                        }
                        else -> {
                            errorMessage.postValue(getStringsUseCase.getSearchFailedString())
                        }
                    }
                    isLoading.postValue(false)
                }
            disposables.add(disposable)
        }
    }

    companion object {
        const val INIT_PAGE_INDEX = 1
    }
}