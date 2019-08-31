package com.ethancrespopueyo.norigintestethan.data.interactor

import com.ethancrespopueyo.norigintestethan.data.db.model.epg.ChannelRoom
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.Channels
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.ScheduleRoom
import java.io.InputStream

interface MainMvpInteractor {

    fun checkIfVoid(): Boolean

    fun insert(channel: ChannelRoom)

    fun arrayFromJson(openRawResource: InputStream): Channels?

    fun inputStreamToString(inputStream: InputStream): String?

    fun insertSchedules(schedule: ScheduleRoom)

    fun updateSchedules(schedule: ScheduleRoom)

    fun selectSchedules(): List<ScheduleRoom>

    fun selectChannelSchedules(title: String): List<ScheduleRoom>

    fun updateStar(int: Int, boolean: Boolean)
}
