package com.ethancrespopueyo.norigintestethan.view.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.UiThread
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ethancrespopueyo.norigintestethan.R
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.ChannelRoom
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.ScheduleRoom
import com.ethancrespopueyo.norigintestethan.presenter.MainPresenter
import com.ethancrespopueyo.norigintestethan.utils.inflate
import com.ethancrespopueyo.norigintestethan.utils.isInternetAvailable
import kotlinx.android.synthetic.main.custom_view.view.*
import okhttp3.internal.platform.Platform
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.text.ParseException
import java.text.SimpleDateFormat


class RecyclerViewAdapter(var channels: List<ChannelRoom>, private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(), Filterable {

    var channelsList = channels
    lateinit var schedules: List<ScheduleRoom>

    fun resetList() {
        channels = channelsList
        notifyDataSetChanged()
    }

    private lateinit var mainPresenter: MainPresenter

    fun setPresenter(prsenter: MainPresenter) {
        mainPresenter = prsenter
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                val time = System.currentTimeMillis()
                var filteredList: MutableList<ChannelRoom> = ArrayList()
                if (charString.isEmpty()) {
                    filteredList = channelsList as MutableList<ChannelRoom>
                } else {
                    for (row in channelsList) {
                        schedules = mainPresenter.getSchedule(row.title)
                        for (index in 0..schedules.size - 1) {
                            val sdf = SimpleDateFormat("yyyy-MM-dd_hh:mm:ss")

                            val mDate1 = sdf.parse(schedules.get(index).start!!.replace("T", "_").dropLast(6))
                            val startInMilliseconds = mDate1.getTime()

                            val mDate2 = sdf.parse(schedules.get(index).endtime!!.replace("T", "_").dropLast(6))
                            val endInMilliseconds = mDate2.getTime()

                            if (startInMilliseconds < time && endInMilliseconds > time) {
                                if (schedules.get(index).title!!.toLowerCase().contains(charString.toLowerCase())) {
                                    filteredList.add(row)
                                }
                            }
                        }

                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                channels = filterResults.values as ArrayList<ChannelRoom>
                notifyDataSetChanged()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            inflate(
                parent.context,
                R.layout.custom_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(mainPresenter, channels[position], listener)
    }

    override fun getItemCount(): Int {
        return channels.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            mainPresenter: MainPresenter,
            channel: ChannelRoom, listener: (Int) -> Unit
        ) = with(itemView) {

            if (isInternetAvailable(context))
                Glide.with(this).load(channel.logo).into(itemView.imageView)

            val time = System.currentTimeMillis()
            doAsync {
                val schedules = mainPresenter.getSchedule(channel.title)
                uiThread {
                for (index in 0..schedules.size - 1) {
                    val sdf = SimpleDateFormat("yyyy-MM-dd_hh:mm:ss")
                    try {
                        val mDate1 = sdf.parse(schedules.get(index).start!!.replace("T", "_").dropLast(6))
                        val startInMilliseconds = mDate1.getTime()

                        val mDate2 = sdf.parse(schedules.get(index).endtime!!.replace("T", "_").dropLast(6))
                        val endInMilliseconds = mDate2.getTime()

                        if (startInMilliseconds < time && endInMilliseconds > time) {

                            if (schedules.get(index).star!!)
                                ivStar.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent))

                            //extract & set program duration
                            itemView.txtName.text = schedules.get(index).title
                            val timeStart = schedules.get(index).start!!.split("T")[1].split("+")[0].dropLast(3)
                            val timeEnd = schedules.get(index).endtime!!.split("T")[1].split("+")[0].dropLast(3)
                            itemView.txtTime.text = timeStart + " - " + timeEnd

                            //Calculate & Set program progress to progress bar
                            val duration = endInMilliseconds - startInMilliseconds
                            val ellapsedTime = time - startInMilliseconds
                            progressBar.max = duration.toInt()
                            progressBar.progress = ellapsedTime.toInt()


                                ivStar.setOnClickListener {

                                        if(schedules.get(index).star){
                                            schedules.get(index).star = false
                                            mainPresenter.updateStar(schedules.get(index).id, false)
                                        }else{
                                            schedules.get(index).star = true
                                            mainPresenter.updateStar(schedules.get(index).id, true)
                                        }


                                }


                            break
                        }
                    } catch (e: ParseException) {
                        e.printStackTrace()
                    }

                }}
            }
        }


    }
}