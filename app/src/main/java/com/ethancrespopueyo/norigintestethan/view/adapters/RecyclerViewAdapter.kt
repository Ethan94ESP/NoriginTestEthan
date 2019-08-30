package com.ethancrespopueyo.norigintestethan.view.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.ChannelRoom
import com.ethancrespopueyo.norigintestethan.utils.inflate
import com.ethancrespopueyo.norigintestethan.utils.isInternetAvailable
import kotlinx.android.synthetic.main.custom_view.view.*
import java.text.ParseException
import java.text.SimpleDateFormat


class RecyclerViewAdapter(var channels: List<ChannelRoom>, private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(), Filterable {

    val channelsList = channels

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                var filteredList: MutableList<ChannelRoom> = ArrayList()
                if (charString.isEmpty()) {
                    filteredList = channelsList as MutableList<ChannelRoom>
                } else {
                    for (row in channelsList) {
                        if (row.title.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
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

    fun resetList() {
        channels = channelsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            inflate(
                parent.context,
                com.ethancrespopueyo.norigintestethan.R.layout.custom_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(channels[position], listener)
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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(channel: ChannelRoom, listener: (Int) -> Unit) = with(itemView) {

            if (isInternetAvailable(context))
                Glide.with(this).load(channel.logo).into(itemView.imageView)

            val time = System.currentTimeMillis()

            for (index in 0..channel.schedules.size - 1) {
                val sdf = SimpleDateFormat("yyyy-MM-dd_hh:mm:ss")
                try {
                    val mDate1 = sdf.parse(channel.schedules.get(index).start!!.replace("T", "_").dropLast(6) )
                    val startInMilliseconds = mDate1.getTime()

                    val mDate2 = sdf.parse(channel.schedules.get(index).end!!.replace("T", "_").dropLast(6) )
                    val endInMilliseconds = mDate2.getTime()

                    if (startInMilliseconds < time && endInMilliseconds > time) {

                        itemView.txtName.text = channel.schedules.get(index).title
                        val timeStart = channel.schedules.get(index).start!!.split("T")[1].split("+")[0].dropLast(3)
                        val timeEnd = channel.schedules.get(index).end!!.split("T")[1].split("+")[0].dropLast(3)
                        itemView.txtTime.text = timeStart + " - " + timeEnd

                        progressBar.progress = 50

                        break
                    }
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
            }
        }
    }
}