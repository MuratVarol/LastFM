package com.varol.lastfm.data.remote.repository

import com.varol.lastfm.base.BaseRepository
import com.varol.lastfm.base.service
import com.varol.lastfm.data.local.database.dao.AlbumsDao
import com.varol.lastfm.data.local.model.AlbumDo
import com.varol.lastfm.data.local.model.AlbumSearchModel
import com.varol.lastfm.data.remote.Api
import com.varol.lastfm.data.remote.BaseAlbumsResponse
import com.varol.lastfm.data.remote.BaseTracksResponse
import com.varol.lastfm.data.remote.DataHolder
import io.reactivex.Flowable
import io.reactivex.Single

class AlbumRepository(
    private val api: Api,
    private val albumsDao: AlbumsDao

) : BaseRepository() {

    fun getTopAlbums(artist: String): Single<DataHolder<BaseAlbumsResponse>> {
        return service.sendRequest(
            api.getTopAlbums(artist)
        )
    }

    fun getAlbumInfo(albumSearch: AlbumSearchModel): Single<DataHolder<BaseTracksResponse>> {
        return service.sendRequest(
            api.getAlbumDetail(albumSearch.albumName, albumSearch.artist)
        )
    }

    fun getAllSavedAlbums(): Flowable<List<AlbumDo>> {
        return albumsDao.getAlbums()
    }

    fun saveAlbum(albumDo: AlbumDo): Long? {
        return albumsDao.insert(albumDo)
    }

    fun deleteAlbum(albumDo: AlbumDo): Int {
        return albumsDao.delete(albumDo)
    }

    fun isSelectedAlbumSaved(mbid: String): Single<AlbumDo?> {
        return albumsDao.getSelectedAlbum(mbid)
    }

}