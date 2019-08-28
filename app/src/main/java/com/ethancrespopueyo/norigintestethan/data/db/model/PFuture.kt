package com.ethancrespopueyo.norigintestethan.data.db.model

import com.ethancrespopueyo.norigintestethan.data.db.model.catchup.Series
import com.ethancrespopueyo.norigintestethan.data.db.model.catchup.Meta
import com.ethancrespopueyo.norigintestethan.data.db.model.catchup.ChannelImages
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.Images
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class PFuture {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("start")
    @Expose
    var start: String? = null
    @SerializedName("end")
    @Expose
    var end: String? = null
    @SerializedName("lastTimePosition")
    @Expose
    var lastTimePosition: String? = null
    @SerializedName("images")
    @Expose
    var images: Images? = null
    @SerializedName("channelId")
    @Expose
    var channelId: String? = null
    @SerializedName("channelTitle")
    @Expose
    var channelTitle: String? = null
    @SerializedName("channelImages")
    @Expose
    var channelImages: ChannelImages? = null
    @SerializedName("meta")
    @Expose
    var meta: Meta? = null
    @SerializedName("series")
    @Expose
    var series: List<Series>? = null
    @SerializedName("description")
    @Expose
    var description: String? = null

}