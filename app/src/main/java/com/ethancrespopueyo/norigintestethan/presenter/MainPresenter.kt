package com.ethancrespopueyo.norigintestethan.presenter

import android.util.Log
import com.ethancrespopueyo.norigintestethan.data.interactor.MainMvpInteractor
import com.ethancrespopueyo.norigintestethan.view.MainMvpView
import java.io.IOException
import java.io.InputStream
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.Channels
import com.google.gson.Gson


class MainPresenter(var mainView: MainMvpView?, val mainInteractor: MainMvpInteractor) : MainMvpPresenter{

    override fun synchronizeJsonWithRoom(openRawResource: InputStream) {
        val myJson = inputStreamToString(openRawResource)
        val arrayList = Gson().fromJson(myJson, Channels::class.java);
        mainInteractor.saveMainData(arrayList.channels)
        for (item in 0..arrayList.channels!!.size-1)
        Log.d("Title::" , arrayList.channels?.get(item)?.title)
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

    override fun getRecyclerViewItems() {

    }

}