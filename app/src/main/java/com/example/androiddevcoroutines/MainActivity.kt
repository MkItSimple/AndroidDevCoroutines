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


        // Example 3
        // job will still continue after cancellation
        val job = GlobalScope.launch(Dispatchers.Default) {
            Log.d(TAG, "Starting long  running calculation...")

            // if more than 1 seconds will cancel automatically
            withTimeout(1000L){
                for (i in 30..60){
                    if (isActive){
                        Log.d(TAG, "Result for i = $i: ${fib(i)}")
                    }
                }
            }

            Log.d(TAG, "Ending long running  calculation")
        }

    }

    // fibonachi
    fun fib(n: Int): Long {
        return if (n == 0) 0
        else if (n == 1) 1
        else fib(n - 1) + fib(n - 2)
    }
}