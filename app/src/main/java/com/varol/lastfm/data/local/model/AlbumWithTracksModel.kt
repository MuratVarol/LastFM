package com.varol.lastfm.data.local.model

import com.google.gson.annotations.SerializedName

data class AlbumWithTracksModel(

    @SerializedName("name")
    val name : String?,

    @SerializedName("mbid")
    val mbid : String?,

    @SerializedName("artist")
    val artist: String?,

    @SerializedName("image")
    val image: List<ImageModel>,

    @SerializedName("tracks")
    val tracks: TrackList?

)
