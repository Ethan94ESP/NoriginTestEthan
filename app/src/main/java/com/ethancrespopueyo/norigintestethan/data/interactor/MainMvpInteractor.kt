package com.ethancrespopueyo.norigintestethan.data.interactor

import com.ethancrespopueyo.norigintestethan.data.db.model.epg.ChannelRoom
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.Channels
import java.io.InputStream

interface MainMvpInteractor {

    fun insert(channel: ChannelRoom)

    fun arrayFromJson(openRawResource: InputStream): Channels?

    fun inputStreamToString(inputStream: InputStream): String?

}
