package eetu.rouhiainen.parliamentapp
import android.app.Application
import android.content.Context
    /* A static variable that can be accessed from anywhere in the app. */
    class MyApp: Application() {
        override fun onCreate() {
            super.onCreate()
            appContext = this
        }

        companion object {
            lateinit var appContext: Context
        }
    }