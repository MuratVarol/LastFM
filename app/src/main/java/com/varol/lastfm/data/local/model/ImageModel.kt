package com.varol.lastfm.data.local.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageModel(

    @SerializedName("#text")
    val text : String?,

    @SerializedName("size")
    val size : String?

) : Parcelable