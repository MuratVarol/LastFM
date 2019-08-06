package com.varol.lastfm.data.local.model

import com.google.gson.annotations.SerializedName

data class TrackList(

    @SerializedName("track")
    val track: List<TrackModel?>?

)
