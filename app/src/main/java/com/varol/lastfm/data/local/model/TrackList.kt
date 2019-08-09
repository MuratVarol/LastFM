package com.varol.lastfm.data.local.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrackList(

    @SerializedName("track")
    val track: List<TrackModel?>?

) : Parcelable
