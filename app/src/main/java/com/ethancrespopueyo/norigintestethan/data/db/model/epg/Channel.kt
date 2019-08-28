package com.ethancrespopueyo.norigintestethan.data.db.model.epg


import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "channelTbl")
class Channel {
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    var id: String? = null
    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    var title: String? = null
    @ColumnInfo(name = "images")
    @SerializedName("images")
    @Expose
    var images: Images? = null
    @ColumnInfo(name = "schedules")
    @SerializedName("schedules")
    @Expose
    var schedules: List<Schedule>? = null

}