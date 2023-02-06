package eetu.rouhiainen.parliamentapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eetu.rouhiainen.parliamentapp.MyApp

@Database(entities = [Member::class], version = 1, exportSchema = false)
abstract class ParliamentDB: RoomDatabase() {
    abstract val parliamentDao: ParliamentDao
    companion object {
        @Volatile
        private var INSTANCE: ParliamentDB? = null
        // context comes from MyApp, may also be provided as parameter
        fun getInstance(context: Context = MyApp.appContext): ParliamentDB {
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