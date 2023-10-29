package com.harian.quoteapp_mvvm

import android.app.Application
import com.harian.quoteapp_mvvm.api.QuoteService
import com.harian.quoteapp_mvvm.api.RetrofitHelper
import com.harian.quoteapp_mvvm.db.QuoteDatabase
import com.harian.quoteapp_mvvm.repository.QuoteRepository

class QuoteApplication : Application() {
    lateinit var quoteRepository: QuoteRepository
    override fun onCreate() {
        super.onCreate()
        initialize()

    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val quotedatabase = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService, quotedatabase, applicationContext)
    }

}

