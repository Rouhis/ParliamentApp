package eetu.rouhiainen.parliamentapp.data
/**
 * Eetu Rouhiainen
 * 2113716
 * 06.03.2023
 *
 * */

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eetu.rouhiainen.parliamentapp.MyApp.Companion.appContext


@Database(entities = [Member::class], version = 1, exportSchema = false)
abstract class ParliamentDB: RoomDatabase() {
    abstract val parliamentDao: ParliamentDao



    companion object {
        @Volatile
        private var INSTANCE: ParliamentDB? = null
        // context comes from MyApp, may also be provided as parameter
        fun getInstance(context: Context = appContext): ParliamentDB {
            synchronized(this) {
                var instance =
                    INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        ParliamentDB::class.java,
                        "parliament_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}