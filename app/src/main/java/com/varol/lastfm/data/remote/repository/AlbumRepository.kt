package com.varol.lastfm.data.remote.repository

import com.varol.lastfm.base.BaseRepository
import com.varol.lastfm.base.service
import com.varol.lastfm.data.local.model.AlbumSearchModel
import com.varol.lastfm.data.remote.Api
import com.varol.lastfm.data.remote.BaseAlbumsResponse
import com.varol.lastfm.data.remote.BaseTracksResponse
import com.varol.lastfm.data.remote.DataHolder
import io.reactivex.Single

class AlbumRepository(private val api: Api) : BaseRepository() {

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

}