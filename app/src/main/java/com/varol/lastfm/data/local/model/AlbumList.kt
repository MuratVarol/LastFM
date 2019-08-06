package com.varol.lastfm.data.local.model

import com.google.gson.annotations.SerializedName

data class AlbumList(

    @SerializedName("album")
    val artist: List<AlbumModel>

)
