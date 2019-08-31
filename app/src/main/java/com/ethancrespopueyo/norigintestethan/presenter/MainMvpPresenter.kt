package com.ethancrespopueyo.norigintestethan.presenter

import com.ethancrespopueyo.norigintestethan.data.db.model.epg.ChannelRoom
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.ScheduleRoom
import java.io.InputStream

interface MainMvpPresenter {

    fun synchronizeJsonWithRoom(openRawResource: InputStream)

    fun getSchedule(title: String): List<ScheduleRoom>

    fun updateStar(index: Int, boolean: Boolean)
}