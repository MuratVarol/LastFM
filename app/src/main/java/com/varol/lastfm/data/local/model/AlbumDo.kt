package com.varol.lastfm.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "tb_albums")
data class AlbumDo(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("mbid")
    val mbid: String,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,

    @ColumnInfo(name = "artist")
    @SerializedName("artist")
    val artist: String?,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    val image: String?,

    @ColumnInfo(name = "tracks")
    @SerializedName("tracks")
    val tracks: List<String>?

)
