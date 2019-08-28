package com.ethancrespopueyo.norigintestethan.data.interactor

import com.ethancrespopueyo.norigintestethan.data.db.model.epg.Channel

interface MainMvpInteractor {

    fun saveMainData(channels: List<Channel>?)

    fun getMainData()

}
