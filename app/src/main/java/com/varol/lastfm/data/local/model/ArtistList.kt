package com.varol.lastfm.data.local.model

import com.google.gson.annotations.SerializedName

data class ArtistList(

    @SerializedName("artist")
    val artist: List<ArtistModel>

)
