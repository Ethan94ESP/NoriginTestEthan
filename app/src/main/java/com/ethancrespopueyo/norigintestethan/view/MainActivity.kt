package com.ethancrespopueyo.norigintestethan.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ethancrespopueyo.norigintestethan.R
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.Channels
import com.ethancrespopueyo.norigintestethan.data.interactor.MainInteractor
import com.ethancrespopueyo.norigintestethan.data.interactor.MainMvpInteractor
import com.ethancrespopueyo.norigintestethan.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainMvpView {

    private lateinit var mainPresenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter(this, MainInteractor())
        mainPresenter.synchronizeJsonWithRoom()

    }

    override fun setRecyclerViewItems(arrayList: ArrayList<Channels>) {

    }

}
