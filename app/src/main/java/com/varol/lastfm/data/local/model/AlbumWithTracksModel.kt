package com.varol.lastfm.data.local.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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

) : Parcelable
