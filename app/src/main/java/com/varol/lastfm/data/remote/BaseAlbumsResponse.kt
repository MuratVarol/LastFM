package com.varol.lastfm.data.remote

import com.google.gson.annotations.SerializedName
import com.varol.lastfm.data.local.model.AlbumList


data class BaseAlbumsResponse(

    @SerializedName("error")
    val error: Int?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("topalbums")
    val topalbums: AlbumList


)