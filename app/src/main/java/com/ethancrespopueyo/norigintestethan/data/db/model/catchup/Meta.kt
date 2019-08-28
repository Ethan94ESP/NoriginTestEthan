package com.ethancrespopueyo.norigintestethan.data.db.model.catchup

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Meta {

    @SerializedName("year")
    @Expose
    var year: String? = null
    @SerializedName("genres")
    @Expose
    var genres: List<String>? = null
    @SerializedName("cast")
    @Expose
    var cast: List<Cast>? = null
    @SerializedName("creators")
    @Expose
    var creators: List<Creator>? = null

}