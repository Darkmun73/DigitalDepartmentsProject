package net.darkmun.digitaldepartments.activities_recycler


sealed class ActivityInfo {
    data class MyActivityInfo (
        val distance: String,
        val time: String,
        val activityName: String,
        val activityDate: String,
        val startTime: String,
        val finishTime: String
    ) : ActivityInfo()

    data class UserActivityInfo(
        val distance: String,
        val time: String,
        val activityName: String,
        val activityDate: String,
        val startTime: String,
        val finishTime: String,
        val userName: String
    ) : ActivityInfo()

    data class DateSectionInfo(
        val activityDate: String
    ) : ActivityInfo()
}