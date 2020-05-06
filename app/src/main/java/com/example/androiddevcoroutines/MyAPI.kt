package com.example.androiddevcoroutines

import retrofit2.Call
import retrofit2.http.GET

interface MyAPI {

    @GET("quotes")
    suspend fun getQuotes() : List<Quote>
}