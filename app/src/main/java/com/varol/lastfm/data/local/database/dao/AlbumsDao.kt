package com.varol.lastfm.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.varol.lastfm.data.local.model.AlbumDo
import io.reactivex.Single

@Dao
interface AlbumsDao : BaseDao<AlbumDo> {
    @Query("SELECT * FROM tb_albums")
    fun getAlbums(): Single<List<AlbumDo>>


    @Query("SELECT * FROM tb_albums WHERE id==:albumId")
    fun getSelectedAlbum(albumId: String): Single<AlbumDo>

}