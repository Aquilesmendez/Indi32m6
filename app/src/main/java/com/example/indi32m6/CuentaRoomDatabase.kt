package com.example.indi32m6

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Cuenta::class), version = 2, exportSchema = false)
public abstract class CuentaRoomDatabase : RoomDatabase() {

    abstract fun cuentaDao(): CuentaDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CuentaRoomDatabase? = null

        fun getDatabase(context: Context): CuentaRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CuentaRoomDatabase::class.java,
                    "cuenta_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
