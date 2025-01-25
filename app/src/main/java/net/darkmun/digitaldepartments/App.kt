package net.darkmun.digitaldepartments

import android.app.Application
import net.darkmun.digitaldepartments.activity.database.ActivityDBSingleton

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ActivityDBSingleton.init(this)
    }
}