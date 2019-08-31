package com.ethancrespopueyo.norigintestethan.data.db.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.ChannelRoom
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.Schedule
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.ScheduleRoom

@Dao
interface ChannelsDao{
    @Query("SELECT * FROM channelTbl ORDER BY id DESC")
    fun allChannel(): LiveData<List<ChannelRoom>>

    @Query("SELECT * FROM schedule")
    fun allSchedules(): List<ScheduleRoom>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(channel: ChannelRoom)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSchedule(schedule: ScheduleRoom)

    @Query("UPDATE schedule SET start = :start, endtime = :end WHERE id = :id")
    fun updateSchedules(start: String, end: String, id: Int)

    @Query("SELECT count(*) FROM channelTbl")
    fun getChannelCount(): Int

    @Query("DELETE FROM channelTbl")
    fun flushChannelData()

    @Query("SELECT * FROM schedule WHERE owner = :title")
    fun allChannelSchedules(title: String): List<ScheduleRoom>

    @Query("UPDATE schedule SET star = :clicked WHERE id = :id")
    fun updateStart(id: Int, clicked: Boolean)
}