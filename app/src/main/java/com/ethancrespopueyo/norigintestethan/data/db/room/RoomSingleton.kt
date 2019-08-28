package com.ethancrespopueyo.norigintestethan.data.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ethancrespopueyo.norigintestethan.data.db.model.epg.Channel

@Database(entities = arrayOf(Channel::class), version = 1, exportSchema = false)
abstract class RoomSingleton : RoomDatabase() {

    abstract fun channelDao(): ChannelsDao

    companion object {
        var TEST_MODE: Boolean = false
        private var INSTANCE: RoomSingleton? = null
        fun getInstance(context: Context): RoomSingleton {
            if (INSTANCE == null) {
                if (TEST_MODE)
                    INSTANCE = Room.inMemoryDatabaseBuilder(context, RoomSingleton::class.java).allowMainThreadQueries()
                        .build()
                else
                    INSTANCE = Room.databaseBuilder(context, RoomSingleton::class.java, "roomdb").build()
            }

            return INSTANCE as RoomSingleton
        }
    }
}