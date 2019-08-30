package com.ethancrespopueyo.norigintestethan.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ethancrespopueyo.norigintestethan.R
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.ChannelRoom
import com.ethancrespopueyo.norigintestethan.utils.inflate
import com.ethancrespopueyo.norigintestethan.utils.isInternetAvailable
import kotlinx.android.synthetic.main.custom_view.view.*


class RecyclerViewAdapter(val channels: List<ChannelRoom>, private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate(parent.context, R.layout.custom_view, parent, false))
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