package com.varol.lastfm.usecase

import android.content.Context
import com.varol.lastfm.R

class GetStringsUseCase(
    private val context: Context
) {
    fun getSearchFailedString() = context.getString(R.string.search_failed)
    fun getFailedToGetAlbumString() = context.getString(R.string.failed_album_fetch)
    fun getNoArtistFoundString() = context.getString(R.string.no_artist_found)
}