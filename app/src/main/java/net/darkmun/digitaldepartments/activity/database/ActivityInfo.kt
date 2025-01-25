package net.darkmun.digitaldepartments.activity.database

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime

enum class ActivityType {
    BICYCLE, RUNNING, WALKING
}

data class Location(
    val latitude: Double,
    val longitude: Double
)

sealed class ActivityInfo {
    @Entity
    data class MyActivityInfo (
        @PrimaryKey(autoGenerate = true) val id : Int,
        @ColumnInfo(name="activity_type") val activityType : ActivityType,
        @ColumnInfo(name="start_date_time") val startDateTime : LocalDateTime,
        @ColumnInfo(name="finish_date_time") val finishDateTime : LocalDateTime,
        @ColumnInfo(name="path") val path : List<Location>
    ) : ActivityInfo() {
        @ColumnInfo(name="distance") var distance: Float
        @ColumnInfo(name="duration") var duration: Duration

        init {
            distance = calculateDistance()
            duration = Duration.between(startDateTime, finishDateTime)
        }

        private fun calculateDistance() : Float {
            var distance = 0F
            for (i in 1..<path.size) {
                val loc1 = android.location.Location("").apply {
                    latitude = path[i].latitude
                    longitude = path[i].longitude
                }
                val loc2 = android.location.Location("").apply {
                    latitude = path[i-1].latitude
                    longitude = path[i-1].longitude
                }
                distance += loc1.distanceTo(loc2)
            }
            Log.w("calculate", distance.toString())
            return distance
        }

        fun getType(): ActivityType {
            return activityType
        }

        fun getDate(): LocalDateTime {
            return finishDateTime
        }

        fun getStartTime(): LocalTime {
            return startDateTime.toLocalTime()
        }

        fun getFinishTime(): LocalTime {
            return finishDateTime.toLocalTime()
        }
    }

    data class UserActivityInfo(
        val distance: String,
        val duration: String,
        val activityName: String,
        val activityDate: String,
        val startTime: String,
        val finishTime: String,
        val userName: String,
        val commentary: String
    ) : ActivityInfo()

    data class DateSectionInfo(
        val activityDate: String
    ) : ActivityInfo()
}