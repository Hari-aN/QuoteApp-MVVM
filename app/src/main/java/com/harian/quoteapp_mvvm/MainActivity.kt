package com.harian.quoteapp_mvvm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.harian.quoteapp_mvvm.api.QuoteService
import com.harian.quoteapp_mvvm.api.RetrofitHelper
import com.harian.quoteapp_mvvm.models.Result
import com.harian.quoteapp_mvvm.repository.QuoteRepository
import com.harian.quoteapp_mvvm.viewmodels.QuoteViewModel
import com.harian.quoteapp_mvvm.viewmodels.QuoteViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var quoteViewModel: QuoteViewModel
    private val quoteText: TextView
        get() = findViewById(R.id.quoteText)
    private val quoteAuthor: TextView
        get() = findViewById(R.id.quoteAuthor)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = (application as QuoteApplication).quoteRepository
        quoteViewModel = ViewModelProvider(
            this,
            QuoteViewModelFactory(repository)
        ).get(QuoteViewModel::class.java)

        //Here Observing the LiveData of QuoteList
        quoteViewModel.quotes.observe(this, Observer {
            updateUI()
        })
    }

    fun updateUI() {
        quoteViewModel.getQuote()?.let { setQuote(it) }
    }

    fun setQuote(quote: Result) {
        quoteText.text = quote.content
        quoteAuthor.text = quote.author
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, quoteViewModel.getQuote()?.content)
        startActivity(intent)
    }

    fun onNext(view: View) {
        quoteViewModel.getNextQuote()?.let { setQuote(it) }
    }

    fun onPrevious(view: View) {
        quoteViewModel.getPreviousQuote()?.let { setQuote(it) }
    }
}