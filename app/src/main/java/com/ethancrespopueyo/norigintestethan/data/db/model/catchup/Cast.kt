package com.ethancrespopueyo.norigintestethan.data.db.model.catchup

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Cast {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("role")
    @Expose
    var role: String? = null

}