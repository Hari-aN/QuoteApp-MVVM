package com.harian.quoteapp_mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harian.quoteapp_mvvm.models.QuoteList
import com.harian.quoteapp_mvvm.repository.QuoteRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {
    private var index = 0

    init {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            repository.getQuotesFromAPI(1)
        }
    }

    val quotes: LiveData<QuoteList>
        get() = repository.quotes

    fun getQuote() = quotes.value?.results?.get(index)

    fun getNextQuote() = quotes.value?.results?.get(++index)

    fun getPreviousQuote() = quotes.value?.results?.get(--index)
}