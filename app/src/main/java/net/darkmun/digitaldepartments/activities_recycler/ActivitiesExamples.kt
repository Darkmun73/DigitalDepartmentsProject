package net.darkmun.digitaldepartments.activities_recycler

class ActivitiesExamples {

    companion object {
        private val myActivities : List<ActivityInfo> = listOf(
            ActivityInfo.DateSectionInfo("Вчера"),
            ActivityInfo.MyActivityInfo(
                "14.32 км",
                "2 часа 46 минут",
                "Серфинг",
                "14 часов назад",
                "14:49",
                "16:31"
            ),
            ActivityInfo.DateSectionInfo("Май 2022 года"),
            ActivityInfo.MyActivityInfo(
                "1000 м",
                "60 минут",
                "Велосипед",
                "29.05.2022",
                "17:30",
                "18:00"
            )
        )

        private val usersActivities : List<ActivityInfo> = listOf(
            ActivityInfo.DateSectionInfo("Вчера"),
            ActivityInfo.UserActivityInfo(
                "14.32 км",
                "2 часа 46 минут",
                "Серфинг",
                "14 часов назад",
                "14:49",
                "16:31",
                "van_darkholme",
                "Я бежал очень сильно, ты так не сможешь"
            ),
            ActivityInfo.UserActivityInfo(
                "228 м",
                "14 часов 48 минут",
                "Качели",
                "14 часов назад",
                "00:00",
                "14:48",
                "techniquepasha",
                ""
            ),
            ActivityInfo.UserActivityInfo(
                "10 км",
                "1 час 10 минут",
                "Езда на кадилак",
                "14 часов назад",
                "12:00",
                "13:10",
                "morgen_shtern",
                ""
            )
        )

        fun getMyActivitiesAndDateSections() : List<ActivityInfo> {
            return myActivities
        }

        fun getUsersActivitiesAndDateSections() : List<ActivityInfo> {
            return usersActivities
        }
    }
}