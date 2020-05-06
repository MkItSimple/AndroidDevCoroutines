package com.example.androiddevcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            delay(3000L) // after 3 seconds will Log below
            Log.d(TAG, "Coroutine hello from thread ${Thread.currentThread().name}")
        }
        Log.d(TAG, "Hello from thread ${Thread.currentThread().name}")
    }
}
