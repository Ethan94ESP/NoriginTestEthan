package com.ethancrespopueyo.norigintestethan.data.db.model.epg

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Channels {

    @SerializedName("channels")
    @Expose
    var channels: List<Channel>? = null

}