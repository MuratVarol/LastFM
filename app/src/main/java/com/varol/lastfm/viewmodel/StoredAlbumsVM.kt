package com.varol.lastfm.viewmodel

import com.varol.lastfm.base.BaseVM
import com.varol.lastfm.usecase.SearchArtistUseCase


class StoredAlbumsVM(
    private val searchArtistUseCase: SearchArtistUseCase
) : BaseVM() {

    init {

        val disposable = searchArtistUseCase
            .searchArtist("cher")
            .observeOn(getBackgroundScheduler())
            .subscribeOn(getBackgroundScheduler())
            .subscribe { t1, t2 ->
                t1
            }
        disposables.add(disposable)

    }

}