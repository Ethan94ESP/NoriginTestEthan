package com.ethancrespopueyo.norigintestethan.presenter

import java.io.InputStream

interface MainMvpPresenter {

    fun inputStreamToString(inputStream: InputStream): String?

    fun synchronizeJsonWithRoom(openRawResource: InputStream)

    fun getRecyclerViewItems()

}