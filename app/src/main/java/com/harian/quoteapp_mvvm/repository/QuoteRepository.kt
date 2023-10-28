package com.harian.quoteapp_mvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.harian.quoteapp_mvvm.api.QuoteService
import com.harian.quoteapp_mvvm.models.QuoteList

class QuoteRepository(private val quoteService: QuoteService) {

    private val quotesLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int) {
        val result = quoteService.getQuotes(page)
        if (result?.body() != null) {
            quotesLiveData.postValue(result.body())
        }
    }
}