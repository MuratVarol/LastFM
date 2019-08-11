package com.varol.lastfm

import com.varol.lastfm.data.local.model.*
import com.varol.lastfm.usecase.AlbumDbMapperUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class AlbumDbMapperUseCaseTest {

    @Test
    fun `should AlbumWithTracks model to AlbumDo mapping match`() {

        //Given
        val albumDbMapperUseCase = AlbumDbMapperUseCase()
        val albumWithTracksModel = createDummyAlbumWithTracksModel()
        val albumDo = createDummyAlbumDoModel()

        //When
        val mappedAlbumDo = albumDbMapperUseCase.mapAlbumWithTracksToAlbumDo(albumWithTracksModel)

        //Then
        assertEquals(mappedAlbumDo, albumDo)


    }

    private fun createDummyAlbumWithTracksModel(): AlbumWithTracksModel {

        val imageModelList = mutableListOf<ImageModel>().apply {
            this.add(
                ImageModel(
                    "https://lastfm-img2.akamaized.net/i/u/34s/3b54885952161aaea4ce2965b2db1638.png",
                    "small"
                )
            )
            this.add(
                ImageModel(
                    "https://lastfm-img2.akamaized.net/i/u/64s/3b54885952161aaea4ce2965b2db1638.png",
                    "medium"
                )
            )
            this.add(
                ImageModel(
                    "https://lastfm-img2.akamaized.net/i/u/174s/3b54885952161aaea4ce2965b2db1638.png",
                    "large"
                )
            )
            this.add(
                ImageModel(
                    "https://lastfm-img2.akamaized.net/i/u/300x300/3b54885952161aaea4ce2965b2db1638.png",
                    "extralarge"
                )
            )
        }

        val trackModelList = mutableListOf<TrackModel>().apply {
            this.add(TrackModel("Believe"))
            this.add(TrackModel("The Power"))
            this.add(TrackModel("Runaway"))
            this.add(TrackModel("All or Nothing"))
            this.add(TrackModel("Strong Enough"))
            this.add(TrackModel("Dov'e L'amore"))
            this.add(TrackModel("Takin' Back My Heart"))
            this.add(TrackModel("Taxi Taxi"))
            this.add(TrackModel("Love Is the Groove"))
            this.add(TrackModel("We All Sleep Alone"))
        }

        val trackList = TrackList(trackModelList)

        return AlbumWithTracksModel(
            name = "Believe",
            mbid = "63b3a8ca-26f2-4e2b-b867-647a6ec2bebd",
            artist = "Cher",
            image = imageModelList,
            tracks = trackList
        )
    }


    private fun createDummyAlbumDoModel(): AlbumDo {

        return AlbumDo(
            name = "Believe",
            mbid = "63b3a8ca-26f2-4e2b-b867-647a6ec2bebd",
            artist = "Cher",
            image = "https://lastfm-img2.akamaized.net/i/u/300x300/3b54885952161aaea4ce2965b2db1638.png",
            tracks = listOf(
                "Believe",
                "The Power",
                "Runaway",
                "All or Nothing",
                "Strong Enough",
                "Dov'e L'amore",
                "Takin' Back My Heart",
                "Taxi Taxi",
                "Love Is the Groove",
                "We All Sleep Alone"
            )
        )
    }

}