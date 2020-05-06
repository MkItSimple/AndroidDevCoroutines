package com.example.androiddevcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Main, IO, Default, Unconfined, newSingleThreadContext
        // You can only change the UI in the Main thread
        // IO used for all kinds of data operations. . . network, database, or writing to files
        // Default if you are planning on doing complex and long running calculations that will  block the main thread
        // Default Ex. if you want to sort 10,000 elements
        // Dispatchers.Unconfined is not confined to specific threads
        // .launch(newSingleThreadContext("MyThread")) will start a new thread . . and run the coroutine in that new created thread

        // Switching Threads
        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Starting coroutine in thread ${Thread.currentThread().name}")
            val answer = doNetworkCall()
            withContext(Dispatchers.Main){
                Log.d(TAG, "Setting text in thread ${Thread.currentThread().name}")
                tvDummy.text = answer
            }
        }
    }

    suspend fun doNetworkCall(): String {
        delay(3000L)
        return "This is the answer"
    }
}