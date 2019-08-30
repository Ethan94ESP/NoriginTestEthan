package com.ethancrespopueyo.norigintestethan.presenter

import android.os.Build
import android.util.Log
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.Channel
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.ChannelRoom
import com.ethancrespopueyo.norigintestethan.data.interactor.MainMvpInteractor
import com.ethancrespopueyo.norigintestethan.view.MainMvpView
import java.io.IOException
import java.io.InputStream
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.Channels
import com.ethancrespopueyo.norigintestethan.data.interactor.ChannelViewModel
import com.ethancrespopueyo.norigintestethan.utils.getCurrentDate
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class MainPresenter(val mainView: MainMvpView?, model: ChannelViewModel) : MainMvpPresenter {
    val channelModel = model

    override fun synchronizeJsonWithRoom(openRawResource: InputStream) {
        val arrayList = channelModel.arrayFromJson(openRawResource)
        doAsync {
            if (arrayList != null) {
                for (item in 0..arrayList.channels!!.size - 1) {
                    for (index in 0..arrayList.channels!!.get(item).schedules!!.size - 1) {
                        var start = arrayList.channels!!.get(item).schedules!!.get(index).start
                        var end = arrayList.channels!!.get(item).schedules!!.get(index).end
                        val startDate = start!!.split("T")[0]
                        val endDate = end!!.split("T")[0]

                        val currentDate = getCurrentDate()
                        arrayList.channels!!.get(item).schedules!!.get(index).start = start.replace(startDate, currentDate)
                        arrayList.channels!!.get(item).schedules!!.get(index).end = end.replace(endDate, currentDate)

                    }
                    channelModel.insert(
                        ChannelRoom(
                            arrayList.channels!!.get(item).id,
                            arrayList.channels!!.get(item).title!!,
                            arrayList.channels!!.get(item).images!!.logo!!,
                            arrayList.channels!!.get(item).schedules!!
                        )
                    )
                }
            }
        }
    }

}