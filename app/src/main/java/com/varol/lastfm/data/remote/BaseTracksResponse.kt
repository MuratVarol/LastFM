package com.varol.lastfm.data.remote

import com.google.gson.annotations.SerializedName
import com.varol.lastfm.data.local.model.AlbumWithTracksModel


data class BaseTracksResponse(

    @SerializedName("error")
    val error: Int?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("album")
    val album: AlbumWithTracksModel


)