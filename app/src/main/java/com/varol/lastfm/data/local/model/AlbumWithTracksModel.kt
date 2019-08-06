package com.varol.lastfm.data.local.model

import com.google.gson.annotations.SerializedName

data class AlbumWithTracksModel(

    @SerializedName("name")
    val name : String?,

    @SerializedName("mbid")
    val mbid : String?,

    @SerializedName("image")
    val image: ImageModel,

    @SerializedName("tracks")
    val tracks: TrackList?

)
