package com.ethancrespopueyo.norigintestethan.data.db.model.epg

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Schedule {

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("start")
    @Expose
    var start: String? = null
    @SerializedName("end")
    @Expose
    var end: String? = null

}
