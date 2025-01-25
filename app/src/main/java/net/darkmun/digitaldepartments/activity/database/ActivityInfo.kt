package net.darkmun.digitaldepartments.activity.database

import android.location.Location
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime

enum class ActivityType {
    BICYCLE, RUNNING, WALKING
}

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
            get() {
                // Distance in km
                return field / 1000
            }
        @ColumnInfo(name="duration") var duration: Duration

        init {
            distance = calculateDistance()
            duration = Duration.between(finishDateTime, startDateTime)
        }

        private fun calculateDistance() : Float {
            var distance = 0F
            for (i in 1..<path.size) {
                distance += path[i].distanceTo(path[i-1])
            }
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