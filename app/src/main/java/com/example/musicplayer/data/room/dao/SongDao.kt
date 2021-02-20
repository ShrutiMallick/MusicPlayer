package com.example.musicplayer.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.musicplayer.data.room.entity.SongEntity

/**
 * Song Dao, Here, Write all your db query
 *

 */

@Dao
interface SongDao {

    @Query("select * from song order by name")
    fun getAll(): LiveData<List<SongEntity>>

    @Query("select * from song where id=:id")
    suspend fun getById(id: String): SongEntity?

    @Query("select count(*) from song")
    fun getCount(): Int

    @Insert(onConflict = REPLACE)
    suspend fun insert(entity: SongEntity)

    @Insert(onConflict = REPLACE)
    suspend fun insert(list: List<SongEntity>)

    @Update()
    suspend fun update(list: SongEntity)
}
