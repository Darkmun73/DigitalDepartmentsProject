package net.darkmun.digitaldepartments.activity.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MyActivityDAO {
    @Query("SELECT * FROM myactivityinfo")
    fun getAll() : LiveData<List<ActivityInfo.MyActivityInfo>>

    @Insert
    fun insert(activityInfo : ActivityInfo.MyActivityInfo)

    @Delete
    fun delete(activityInfo : ActivityInfo.MyActivityInfo)
}