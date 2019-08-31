package com.ethancrespopueyo.norigintestethan.data.db.model.epg

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "schedule",
    foreignKeys = arrayOf(
        ForeignKey(entity = ChannelRoom::class,
            parentColumns = arrayOf("title"),
            childColumns = arrayOf("owner"))))
data class ScheduleRoom (@PrimaryKey(autoGenerate = true) val id: Int,  val owner: String, val title: String, var star: Boolean, var start:String, var endtime:String)

class Schedule {

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("start")
    @Expose
    var start: String? = null
    @SerializedName("end")
    @Expose
    var end: String? = null

    var star: Boolean? = false

}