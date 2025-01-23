package net.darkmun.digitaldepartments.activity.new_activity

import net.darkmun.digitaldepartments.R

class ActivityTypes {
    companion object {
        private val types : List<ActivityTypeInfo> = listOf(
            ActivityTypeInfo("Велосипед", R.drawable.ic_activity_type_bicycles),
            ActivityTypeInfo("Бег", R.drawable.ic_activity_type_bicycles),
            ActivityTypeInfo("Шаг", R.drawable.ic_activity_type_bicycles)
        )

        fun get() : List<ActivityTypeInfo> {
            return types
        }
    }
}