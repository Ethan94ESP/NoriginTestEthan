package com.ethancrespopueyo.norigintestethan.presenter

import android.view.View
import com.ethancrespopueyo.norigintestethan.R
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.ChannelRoom
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.ScheduleRoom
import com.ethancrespopueyo.norigintestethan.view.MainMvpView
import java.io.InputStream
import com.ethancrespopueyo.norigintestethan.data.interactor.ChannelViewModel
import com.ethancrespopueyo.norigintestethan.utils.getCurrentDate
import kotlinx.android.synthetic.main.activity_main.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainPresenter(val mainView: MainMvpView?, model: ChannelViewModel) : MainMvpPresenter, View.OnClickListener {

    val channelModel = model

    override fun synchronizeJsonWithRoom(openRawResource: InputStream) {
        val arrayList = channelModel.arrayFromJson(openRawResource)
        doAsync {
            if (arrayList != null) {
                if (channelModel.checkIfVoid())
                    for (item in 0..arrayList.channels!!.size - 1) {
                        channelModel.insert(
                            ChannelRoom(
                                arrayList.channels!!.get(item).id,
                                arrayList.channels!!.get(item).title!!,
                                arrayList.channels!!.get(item).images!!.logo!!
                            )
                        )

                        for (index in 0..arrayList.channels!!.get(item).schedules!!.size - 1) {
                            var start = arrayList.channels!!.get(item).schedules!!.get(index).start
                            var end = arrayList.channels!!.get(item).schedules!!.get(index).end
                            val startDate = start!!.split("T")[0]
                            val endDate = end!!.split("T")[0]

                            val currentDate = getCurrentDate()
                            arrayList.channels!!.get(item).schedules!!.get(index).start =
                                start.replace(startDate, currentDate)
                            arrayList.channels!!.get(item).schedules!!.get(index).end =
                                end.replace(endDate, currentDate)

                            channelModel.insertSchedules(
                                ScheduleRoom(
                                    0,
                                    arrayList.channels!!.get(item).title!!,
                                    arrayList.channels!!.get(item).schedules!!.get(index).title!!,
                                    arrayList.channels!!.get(item).schedules!!.get(index).star!!,
                                    arrayList.channels!!.get(item).schedules!!.get(index).start!!,
                                    arrayList.channels!!.get(item).schedules!!.get(index).end!!

                                )
                            )
                        }
                    } else {
                    var schedules = channelModel.selectSchedules()
                    for (index in 0..schedules.size - 1) {
                        var start = schedules!!.get(index).start
                        var end = schedules!!.get(index).endtime
                        val startDate = start!!.split("T")[0]
                        val endDate = end!!.split("T")[0]

                        val currentDate = getCurrentDate()
                        schedules!!.get(index).start =
                            start.replace(startDate, currentDate)
                        schedules!!.get(index).endtime =
                            end.replace(endDate, currentDate)

                        channelModel.updateSchedules(schedules!!.get(index))

                    }
                }
            }
        }
    }

    override fun getSchedule(title: String): List<ScheduleRoom> {
        return channelModel.selectChannelSchedules(title)
    }

    override fun updateStar(index: Int, boolean: Boolean) {
        doAsync {
            channelModel.updateStar(index, boolean)
            uiThread {
                mainView!!.notifyDataSetChanged()
            }
        }
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.rlCatchup -> mainView!!.notifyDataSetChanged()
        }
    }
}