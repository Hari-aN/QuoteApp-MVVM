package com.harian.quoteapp_mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var quoteViewModel: QuoteViewModel
    private val quoteText: TextView
        get() = findViewById(R.id.quoteText)
    private val quoteAuthor: TextView
        get() = findViewById(R.id.quoteAuthor)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quotesAPI = RetrofitHelper.getInstance().create(QuotesAPI::class.java)
        GlobalScope.launch {
            val result = quotesAPI.getQuotes(1)
            if (result != null) {
                Log.d("Harian : ", result.body().toString())
            }
        }

        quoteViewModel = ViewModelProvider(
            this,
            QuoteViewModelFactory(application)
        ).get(QuoteViewModel::class.java)

        setQuote(quoteViewModel.getQuote())
    }

    fun setQuote(quote: Quote) {
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, quoteViewModel.getQuote().text)
        startActivity(intent)
    }

    fun onNext(view: View) {
        setQuote(quoteViewModel.getNextQuote())
    }

    fun onPrevious(view: View) {
        setQuote(quoteViewModel.getPreviousQuote())
    }
}