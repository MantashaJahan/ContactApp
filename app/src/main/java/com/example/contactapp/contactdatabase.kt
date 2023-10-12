package com.example.contactapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [contact::class], version = 1)
abstract class contactDatabase: RoomDatabase() {
    abstract fun contactDao():contactDao

    companion object{
        @Volatile
        private var INSTANCE:contactDatabase?=null

        fun getDatabase(context: Context):contactDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    contactDatabase::class.java,
                    "contact_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}