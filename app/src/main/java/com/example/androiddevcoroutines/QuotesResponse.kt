package com.example.androiddevcoroutines

data class QuotesResponse (
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)