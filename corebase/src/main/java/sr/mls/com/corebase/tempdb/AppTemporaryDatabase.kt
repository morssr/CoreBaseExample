package sr.mls.com.corebase.tempdb

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

const val TAG = "AppTemporaryDatabase"
//
//@Database(
//    entities = [AppTempGeneral::class],
//    version = 1
//)
abstract class AppTemporaryDatabase : RoomDatabase() {
    //Define data access objects
    //abstract fun alarmDao(): AlarmDao
    /**
     * Initiate default db data.
     */
    private class AppDatabaseCallback : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            Log.d(TAG, "onOpen: db is opened")
        }

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            Log.d(TAG, "onCreate: db is created ${Thread.currentThread().name}")
            INSTANCE?.let { database ->
                GlobalScope.launch {
                    //instantiate dao's
//                    val sequencerSettingsDao = database.sequencerSettingsDao()
                    //set default values
//                    sequencerSettingsDao.insert(AppDbDefault.getDefaultSequencerSettings())
                }
            }
        }
    }

    /**
     * Singleton db object. Instantiate the db.
     */
    companion object {
        @Volatile
        private var INSTANCE: AppTemporaryDatabase? = null

        fun getDatabase(context: Context): AppTemporaryDatabase {
            Log.d(TAG, "getDatabase: start")

            return INSTANCE
                ?: synchronized(this) {
                val instance = Room.inMemoryDatabaseBuilder(
                    context.applicationContext,
                    AppTemporaryDatabase::class.java
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(AppDatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
