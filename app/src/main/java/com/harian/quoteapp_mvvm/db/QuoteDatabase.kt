package com.harian.quoteapp_mvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.harian.quoteapp_mvvm.models.Result

@Database(entities = [Result::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    companion object {
        private var DB_INSTANCE: QuoteDatabase? = null

        fun getDatabase(context: Context): QuoteDatabase {
            if (DB_INSTANCE == null) {
                synchronized(this) {
                    DB_INSTANCE = Room.databaseBuilder(
                        context, QuoteDatabase::class.java, "quoteDB"
                    ).build()
                }
            }
            return DB_INSTANCE!!
        }
    }
}