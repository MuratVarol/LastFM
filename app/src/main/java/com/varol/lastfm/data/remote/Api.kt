package com.varol.lastfm.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("?method=artist.search")
    fun getArtists(
        @Query(value = "artist") artist: String,
        @Query(value = "page") page: Int
    ): Single<BaseArtistResponse>

    @GET("?method=artist.gettopalbums")
    fun getTopAlbums(
        @Query(value = "artist") artist: String
    ): Single<BaseAlbumsResponse>

    @GET("?method=album.getinfo")
    fun getAlbumDetail(
        @Query(value = "album") album: String,
        @Query(value = "artist") artist: String
    ): Single<BaseTracksResponse>
}