package com.varol.lastfm.data.local.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumSearchModel(
    val artist: String,
    val albumName: String
) : Parcelable