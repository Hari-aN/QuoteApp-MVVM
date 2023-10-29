package com.harian.quoteapp_mvvm.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harian.quoteapp_mvvm.models.Result

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuotes(quotes: List<Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes(): List<Result>
}