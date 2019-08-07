package com.varol.lastfm.data.remote.repository

import com.varol.lastfm.base.BaseRepository
import com.varol.lastfm.base.service
import com.varol.lastfm.data.remote.Api
import com.varol.lastfm.data.remote.BaseArtistResponse
import com.varol.lastfm.data.remote.DataHolder
import io.reactivex.Single

class ArtistRepository(private val api: Api) : BaseRepository() {

    fun searchArtist(query: String): Single<DataHolder<BaseArtistResponse>> {
        return service.sendRequest(
            api.getSources(query)
        )
    }

}