package net.darkmun.digitaldepartments.activity.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ActivityInfo.MyActivityInfo::class], version = 1)
@TypeConverters(ActivityConverters::class)
abstract class ActivitiesDatabase : RoomDatabase() {
    abstract fun myActivityDAO() : MyActivityDAO
}