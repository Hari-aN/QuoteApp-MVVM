package com.harian.quoteapp_mvvm

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesAPI {

    // Defines end point after base URL in GET method
    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int) : Response<QuoteList>

    //BaseURL+EndPoint+queryParameter

}