package com.varol.lastfm.data.local.model

import com.google.gson.annotations.SerializedName

data class ResultModel(

    @SerializedName("artistmatches")
    val artistmatches: ArtistList

)
