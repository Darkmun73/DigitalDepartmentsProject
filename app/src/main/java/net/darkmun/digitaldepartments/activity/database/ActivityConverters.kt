package net.darkmun.digitaldepartments.activity.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset


class ActivityConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime): Long {
        return dateTime.toEpochSecond(ZoneOffset.UTC)
    }
    @TypeConverter
    fun toLocalDateTime(value: Long): LocalDateTime {
        return LocalDateTime.ofEpochSecond(value, 0, ZoneOffset.UTC)
    }

    @TypeConverter
    fun fromDuration(duration: Duration): Long {
        return duration.toSeconds()
    }
    @TypeConverter
    fun toDuration(value: Long): Duration {
        return Duration.ofSeconds(value)
    }

    @TypeConverter
    fun fromListOfLocation(list: List<Location>): String {
        return gson.toJson(list)
    }
    @TypeConverter
    fun toListOfLocation(json: String): List<Location> {
        return gson.fromJson(json, object : TypeToken<List<Location>>() {}.type)
    }
}