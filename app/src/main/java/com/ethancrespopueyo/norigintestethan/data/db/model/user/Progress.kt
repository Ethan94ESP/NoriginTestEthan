package com.ethancrespopueyo.norigintestethan.data.db.model.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Progress {

    @SerializedName("percentage")
    @Expose
    var percentage: Double? = null
    @SerializedName("position")
    @Expose
    var position: Int? = null

}