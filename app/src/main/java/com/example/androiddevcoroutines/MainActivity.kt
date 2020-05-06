package com.example.androiddevcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example 3 using async . . same as example 2 but cleaner codes
        // you could also use GlobalScope.async if you want GlobalScope to return something but launch is fine for now :)
        GlobalScope.launch(Dispatchers.IO) {
            // measureTimeMillis will measure total time it takes
            val  time = measureTimeMillis {
                val answer1 = async { networkCall11() }
                val answer2 = async { networkCall12() }

                Log.d(TAG, "Answer1 is ${answer1.await()}") // await will block our current GlobalScope coroutine until answer1 and answer2 is available
                Log.d(TAG, "Answer2 is ${answer2.await()}")
            }

            Log.d(TAG, "Request took $time ms.")
        }

    }

    suspend fun networkCall11() : String {
        delay(3000L)
        return "Answer 1"
    }

    suspend fun networkCall12() : String {
        delay(3000L)
        return "Answer 2"
    }

}

// by default first function need to finish before the second function to start
// Async . . two function run at the same time
// Await


// Example 1 . . takes 6 milliseconds which is not what we want
//GlobalScope.launch(Dispatchers.IO) {
//  val  time = measureTimeMillis {
//      val answer1 = networkCall11()
//      val answer2 = networkCall12()
//      Log.d(TAG, "Answer1 is $answer1")
//      Log.d(TAG, "Answer2 is $answer2")
//  }
//
//  Log.d(TAG, "Request took $time ms.")
//}


// Example 2 . . Takes only 3 seconds . . but the we do it is  very  terrible
//GlobalScope.launch(Dispatchers.IO) {
//    // measureTimeMillis will measure total time it takes
//    val  time = measureTimeMillis {
//        var answer1: String? = null
//        var answer2: String? = null
//
//        val job1 = launch { answer1 = networkCall11() }
//        val job2 = launch { answer2 = networkCall12() }
//
//        job1.join()
//        job2.join()
//
//        Log.d(TAG, "Answer1 is $answer1")
//        Log.d(TAG, "Answer2 is $answer2")
//    }
//
//    Log.d(TAG, "Request took $time ms.")
//}