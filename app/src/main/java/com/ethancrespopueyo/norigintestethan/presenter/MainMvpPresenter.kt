package com.ethancrespopueyo.norigintestethan.presenter

import com.ethancrespopueyo.norigintestethan.data.db.model.epg.Channels

interface MainMvpPresenter {

    fun synchronizeJsonWithRoom()

    fun getRecyclerViewItems()

}