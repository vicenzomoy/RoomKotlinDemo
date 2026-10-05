package edu.uph.m24si2.roomkotlindemo.data

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.sqlite.driver.AndroidSQLiteDriver
@Database(
    entities = [Mahasiswa::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mahasiswaDao(): MahasiswaDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "kampus_database"
                )
                    .setDriver(AndroidSQLiteDriver())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
