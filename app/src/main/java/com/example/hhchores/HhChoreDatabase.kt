package com.example.hhchores

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class HhChoreDatabase : RoomDatabase() {
    companion object {
        private lateinit var hhChoreDatabase: HhChoreDatabase
        fun getDatabase(
            applicationContext: Context
        ): HhChoreDatabase {
            if (!(::hhChoreDatabase.isInitialized)) {
                hhChoreDatabase = Room.databaseBuilder(
                    applicationContext,
                    hhChoreDatabase::class.java,
                    "chore-db"
                ).build()
            }
            return hhChoreDatabase
        }
    }
    abstract fun taskDao(): TaskDao
}