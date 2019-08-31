package com.ethancrespopueyo.norigintestethan.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ethancrespopueyo.norigintestethan.R
import com.ethancrespopueyo.norigintestethan.data.interactor.ChannelViewModel
import com.ethancrespopueyo.norigintestethan.presenter.MainPresenter
import com.ethancrespopueyo.norigintestethan.view.adapters.RecyclerViewAdapter
import com.ethancrespopueyo.norigintestethan.view.adapters.SearchAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainMvpView {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var model: ChannelViewModel
    private lateinit var adapter: RecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeRecyclerVM()
        mainPresenter = MainPresenter(this, model)
        mainPresenter.synchronizeJsonWithRoom(getResources().openRawResource(R.raw.epg))
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        menuInflater.inflate(R.menu.search_view, menu)

        val searchItem = menu.findItem(R.id.app_bar_search)

        if (searchItem!=null) {
            val searchView = searchItem.actionView as SearchView

            val searchHint = getString(R.string.searchHint)
            searchView.setQueryHint(searchHint)

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.toString().isNotEmpty()) {
                        adapter.filter.filter(newText)
                        /*
                        startRecyclerView(generateData(newText))
                        companyList.clear()*/
                    }
                    else {
                        adapter.resetList()
                    }
                    return false
                }
            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun notifyDataSetChanged(){
        adapter.notifyDataSetChanged()
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
            adapter = RecyclerViewAdapter(channels) {}
            adapter.setPresenter(mainPresenter)
            recycler_view.adapter = adapter
        })


    }
}
