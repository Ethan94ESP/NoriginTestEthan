package com.ethancrespopueyo.norigintestethan.data.db.model.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class User {

    @SerializedName("user")
    @Expose
    var user: String? = null
    @SerializedName("programs")
    @Expose
    var programs: List<Program>? = null

}