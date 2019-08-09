package com.varol.lastfm.data.local.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrackModel(

    @SerializedName("name")
    val name: String?

) : Parcelable
