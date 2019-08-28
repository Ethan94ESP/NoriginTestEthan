package com.ethancrespopueyo.norigintestethan.data.db.model.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Program {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("progress")
    @Expose
    var progress: Progress? = null

}