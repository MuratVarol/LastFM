package com.varol.lastfm.usecase

import com.varol.lastfm.data.remote.BaseArtistResponse
import com.varol.lastfm.data.remote.DataHolder
import com.varol.lastfm.data.remote.repository.ArtistRepository
import io.reactivex.Single

class SearchArtistUseCase(
    private val artistRepository: ArtistRepository
) {
    fun searchArtist(query: String, pageIndex: Int): Single<DataHolder<BaseArtistResponse>> {
        return artistRepository.searchArtist(query, pageIndex)
    }
}