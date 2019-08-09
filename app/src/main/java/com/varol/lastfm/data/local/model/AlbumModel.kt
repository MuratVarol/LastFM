package com.varol.lastfm.data.local.model

import com.google.gson.annotations.SerializedName

data class AlbumModel(

    @SerializedName("name")
    val name: String?,

    @SerializedName("mbid")
    val mbid: String?,

    @SerializedName("image")
    val image: List<ImageModel?>?,

    @SerializedName("artist")
    val artist: ArtistModel

) {
    // Get biggest not null image url
    val imageUrl: String?
        get() = image?.filterNotNull()?.last()?.text
}
