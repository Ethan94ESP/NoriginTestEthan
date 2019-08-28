package com.ethancrespopueyo.norigintestethan.data.db.model.catchup

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Series {

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("episodes")
    @Expose
    var episodes: List<Episode>? = null

}
