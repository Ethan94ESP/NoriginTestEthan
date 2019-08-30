package com.ethancrespopueyo.norigintestethan.data.interactor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.Channel
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.ChannelRoom
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.Channels
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

    override fun arrayFromJson(openRawResource: InputStream): Channels? {
        val myJson = inputStreamToString(openRawResource)
        return Gson().fromJson(myJson, Channels::class.java);
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