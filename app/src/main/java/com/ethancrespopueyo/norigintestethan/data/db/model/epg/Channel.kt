package com.ethancrespopueyo.norigintestethan.data.db.model.epg


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Channel {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("images")
    @Expose
    var images: Images? = null
    @SerializedName("schedules")
    @Expose
    var schedules: List<Schedule>? = null

}