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


class RecyclerViewAdapter(var channels: List<ChannelRoom>, private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(), Filterable {

    val channelsList = channels

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                var filteredList: MutableList<ChannelRoom> = ArrayList()
                if (charString.isEmpty()) {
                    filteredList = channelsList as MutableList<ChannelRoom>
                } else {
                    for (row in channelsList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.title.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }

                }

                val filterResults = Filter.FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
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
            itemView.txtName.text = channel.title
            val timeStart = channel.schedules.get(0).start!!.split("T")[1].split("+")[0].dropLast(3)
            val timeEnd = channel.schedules.get(0).end!!.split("T")[1].split("+")[0].dropLast(3)
            itemView.txtTime.text = timeStart + " - " + timeEnd
            if (isInternetAvailable(context))
                Glide.with(this).load(channel.logo).into(itemView.imageView)

        }
    }
}