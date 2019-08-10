package com.varol.lastfm.usecase

import com.varol.lastfm.data.local.model.AlbumDo
import com.varol.lastfm.data.local.model.AlbumWithTracksModel

class AlbumDbMapperUseCase {

    fun mapAlbumWithTracksToAlbumDo(albumWithTracks: AlbumWithTracksModel): AlbumDo {
        return AlbumDo(
            mbid = albumWithTracks.mbid ?: "",
            name = albumWithTracks.name,
            artist = albumWithTracks.artist,
            image = albumWithTracks.image?.filterNotNull()?.last()?.text,
            tracks = albumWithTracks.tracks?.track?.filterNotNull()?.map { it.name }
                ?: emptyList<String>()
        )
    }

    fun mapAlbumWithTrackListToAlbumDoList(albumWithTracksList: List<AlbumWithTracksModel>): List<AlbumDo> {
        return albumWithTracksList.map { albums ->
            mapAlbumWithTracksToAlbumDo(albums)
        }
    }
}