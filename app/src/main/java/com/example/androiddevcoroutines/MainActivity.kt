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

        Log.d(TAG, "Before runBlocking")

        // this will block the main thread
        runBlocking {
            launch {
                delay(3000L)
                Log.d(TAG, "Finished IO Coroutine 1")
            }

            launch {
                delay(3000L)
                Log.d(TAG, "Finished IO Coroutine 2")
            }

            Log.d(TAG, "Start of runBlocking")
            delay(5000L)
            Log.d(TAG, "End of runBlocking")
        }
        Log.d(TAG, "After runBlocking")
    }
}