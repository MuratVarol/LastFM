package com.varol.lastfm.data.local.model

import com.google.gson.annotations.SerializedName

data class ImageModel(

    @SerializedName("#text")
    val text : String?,

    @SerializedName("size")
    val size : String?

)