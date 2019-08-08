package com.varol.lastfm.usecase

import com.varol.lastfm.data.local.model.AlbumSearchModel
import com.varol.lastfm.data.remote.BaseAlbumsResponse
import com.varol.lastfm.data.remote.BaseTracksResponse
import com.varol.lastfm.data.remote.DataHolder
import com.varol.lastfm.data.remote.repository.AlbumRepository
import io.reactivex.Single

class TopAlbumsUseCase(
    private val albumRepository: AlbumRepository
) {
    fun getTopAlbums(artist: String): Single<DataHolder<BaseAlbumsResponse>> {
        return albumRepository.getTopAlbums(artist)
    }

    fun getAlbumDetail(albumSearch: AlbumSearchModel): Single<DataHolder<BaseTracksResponse>> {
        return albumRepository.getAlbumInfo(albumSearch)
    }
}