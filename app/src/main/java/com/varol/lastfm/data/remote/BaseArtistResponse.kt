package com.varol.lastfm.data.remote

import com.google.gson.annotations.SerializedName
import com.varol.lastfm.data.local.model.ResultModel


data class BaseArtistResponse(

    @SerializedName("error")
    val error: Int?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("results")
    val results: ResultModel?

)