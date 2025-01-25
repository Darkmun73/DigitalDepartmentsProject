package net.darkmun.digitaldepartments.activity.new_activity

import androidx.annotation.DrawableRes
import net.darkmun.digitaldepartments.activity.database.ActivityType

data class ActivityTypeInfo(
    val type : ActivityType,
    @DrawableRes val image : Int
)