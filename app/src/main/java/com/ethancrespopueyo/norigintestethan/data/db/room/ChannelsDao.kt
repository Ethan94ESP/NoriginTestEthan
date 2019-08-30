package com.ethancrespopueyo.norigintestethan.data.db.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.ChannelRoom

@Dao
interface ChannelsDao{
    @Query("SELECT * FROM channelTbl ORDER BY id DESC")
    fun allChannel(): LiveData<List<ChannelRoom>>

    @Query("SELECT * FROM channelTbl ORDER BY id DESC Limit 1")
    fun lastChannel(): LiveData<ChannelRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(budget: ChannelRoom)

    @Query("SELECT count(*) FROM channelTbl")
    fun getChannelCount(): Int

    @Query("DELETE FROM channelTbl")
    fun flushChannelData()

    }