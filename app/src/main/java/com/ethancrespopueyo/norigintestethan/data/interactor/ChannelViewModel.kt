package com.ethancrespopueyo.norigintestethan.data.interactor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.*
import com.ethancrespopueyo.norigintestethan.data.db.room.RoomSingleton
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

class ChannelViewModel(application: Application): AndroidViewModel(application), MainMvpInteractor{

    private val db: RoomSingleton = RoomSingleton.getInstance(application)
    internal val allChannels : LiveData<List<ChannelRoom>> = db.channelDao().allChannel()

    override fun insert(channel: ChannelRoom){
        db.channelDao().insert(channel)
    }
    override fun insertSchedules(schedule: ScheduleRoom){
        db.channelDao().insertSchedule(schedule)
    }

    override fun updateSchedules(schedule: ScheduleRoom){
        db.channelDao().updateSchedules(schedule.start, schedule.endtime, schedule.id)
    }


    override fun checkIfVoid(): Boolean {
        return db.channelDao().getChannelCount() == 0;
    }

    override fun arrayFromJson(openRawResource: InputStream): Channels? {
        val myJson = inputStreamToString(openRawResource)
        return Gson().fromJson(myJson, Channels::class.java);
    }

    override fun selectSchedules() : List<ScheduleRoom>{
        return db.channelDao().allSchedules()
    }

    override fun selectChannelSchedules(title: String): List<ScheduleRoom> {
        return db.channelDao().allChannelSchedules(title)
    }

    override fun updateStar(int: Int, boolean: Boolean){
        db.channelDao().updateStart(int, boolean)
    }

    override fun inputStreamToString(inputStream: InputStream): String? {
        try {
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            return String(bytes)
        } catch (e: IOException) {
            return null
        }

    }
}