package net.darkmun.digitaldepartments.activity.database

import android.content.Context
import androidx.room.Room

object ActivityDBSingleton {
    lateinit var roomDB : ActivitiesDatabase

    // used in App class
    fun init(context: Context) {
        roomDB = Room.databaseBuilder(
            context,
            ActivitiesDatabase::class.java,
            "Activities"
        ).build()
    }
}