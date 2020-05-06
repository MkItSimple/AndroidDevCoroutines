package com.example.androiddevcoroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

const val BASE_URL = "https://us-central1-fir-api-2deff.cloudfunctions.net/app/api/"

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val okkHttpclient = OkHttpClient.Builder()
            .build()


        val api = Retrofit.Builder()
            .client(okkHttpclient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyAPI::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val  quotes = api.getQuotes()
//            for (quote in quotes){
//                Log.d(TAG, quote.toString())
//            }
        }
    }

}