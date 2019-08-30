package com.ethancrespopueyo.norigintestethan.data.db.room

import androidx.room.TypeConverter
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.Schedule
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DataConverter {

    @TypeConverter // note this annotation
    fun fromOptionValuesList(optionValues: List<Schedule>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Schedule>>() {

        }.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter // note this annotation
    fun toOptionValuesList(optionValuesString: String?): List<Schedule>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Schedule>>() {

        }.type
        return gson.fromJson(optionValuesString, type)
    }

}