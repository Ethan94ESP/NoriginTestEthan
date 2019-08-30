package com.ethancrespopueyo.norigintestethan.data.db.model.epg

import androidx.room.ColumnInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Images {

    @ColumnInfo(name = "logo")
    @SerializedName("logo")
    @Expose
    var logo: String? = null

}