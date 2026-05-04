package com.hereliesaz.all24.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hereliesaz.all24.data.model.VenueEntity

@Database(entities = [VenueEntity::class], version = 1, exportSchema = false)
abstract class All24Database : RoomDatabase() {
    abstract fun venueDao(): VenueDao

    companion object {
        @Volatile
        private var INSTANCE: All24Database? = null

        fun getDatabase(context: Context): All24Database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    All24Database::class.java,
                    "all24_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
