package com.varol.lastfm.usecase

import com.varol.lastfm.data.local.model.AlbumDo
import com.varol.lastfm.data.local.model.AlbumSearchModel
import com.varol.lastfm.data.remote.BaseAlbumsResponse
import com.varol.lastfm.data.remote.BaseTracksResponse
import com.varol.lastfm.data.remote.DataHolder
import com.varol.lastfm.data.remote.repository.AlbumRepository
import io.reactivex.Flowable
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

    fun getStoredAlbums(): Flowable<List<AlbumDo>> {
        return albumRepository.getAllSavedAlbums()
    }

    fun saveAlbum(albumDo: AlbumDo): Long? {
        return albumRepository.saveAlbum(albumDo)
    }

    fun deleteAlbum(albumDo: AlbumDo): Int? {
        return albumRepository.deleteAlbum(albumDo)
    }

    fun isSelectedAlbumStored(albumDo: AlbumDo): Single<AlbumDo?> {
        return albumRepository.isSelectedAlbumSaved(albumDo.mbid)
    }
}