package com.ethancrespopueyo.norigintestethan.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ethancrespopueyo.norigintestethan.R
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.Channels
import com.ethancrespopueyo.norigintestethan.data.interactor.ChannelViewModel
import com.ethancrespopueyo.norigintestethan.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainMvpView {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var model: ChannelViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeRecyclerVM()
        mainPresenter = MainPresenter(this, model)
        mainPresenter.synchronizeJsonWithRoom(getResources().openRawResource(R.raw.epg))

    }

    override fun initializeRecyclerVM() {

        // Get the view model
        model = ViewModelProviders.of(this).get(ChannelViewModel::class.java)
        // Specify layout for recycler view
        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
        recycler_view.layoutManager = linearLayoutManager

        // Observe the model
        model.allChannels.observe(this, Observer { channels ->
            // Data bind the recycler view
            recycler_view.adapter = RecyclerViewAdapter(channels) {
                //mainPresenter.onItemClick(it)
            }
        })
    }
}
