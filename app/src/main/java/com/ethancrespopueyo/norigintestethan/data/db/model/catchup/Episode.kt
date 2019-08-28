package com.ethancrespopueyo.norigintestethan.data.db.model.catchup

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Episode {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null

}