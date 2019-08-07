package com.varol.lastfm.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("?method=artist.search")
    fun getSources(
        @Query(value = "artist") artist: String
    ): Single<BaseArtistResponse>
}