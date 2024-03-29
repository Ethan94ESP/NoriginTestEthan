package com.ethancrespopueyo.norigintestethan.data.db.model.epg


import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Channel(
    id: String,
    title: String?,
    logo: String?,
    schedules: List<Schedule>?
) {

    @SerializedName("id")
    @Expose
    var id: String = ""

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("images")
    @Expose
    var images: Images? = null

    @SerializedName("schedules")
    @Expose
    var schedules: List<Schedule>? = null

}


@Entity(tableName = "channelTbl")
data class ChannelRoom(
    @ColumnInfo(name = "id")
    var id: String,

    @PrimaryKey
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "logo")
    var logo: String
)