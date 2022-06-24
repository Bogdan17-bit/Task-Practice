package com.example.myapplication.room

import android.content.Context
import androidx.room.*
import com.example.myapplication.models.database.UserDatabase
import com.example.myapplication.utils.Converters

@Database(entities = [UserDatabase::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UserDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context : Context) : AppDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}