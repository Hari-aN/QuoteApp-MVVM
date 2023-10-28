package com.harian.quoteapp_mvvm.api

import com.harian.quoteapp_mvvm.models.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {
    // Defines end point after base URL in GET method
    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int): Response<QuoteList>

    //BaseURL+EndPoint+queryParameter
}