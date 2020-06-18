package com.mobile.droidsOnRoids.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobile.droidsOnRoids.common.DATABASE
import com.mobile.droidsOnRoids.data.entity.Cell

@Database(
    entities = [Cell::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cellDao(): CellDao

    companion object {
        fun buildDatabase(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE).build()
    }
}