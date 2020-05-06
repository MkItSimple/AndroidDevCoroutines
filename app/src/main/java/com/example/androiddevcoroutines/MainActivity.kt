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
            for (i in 30..40){
                // if you don't put this if statement it will still continue to 40 after cancellation
                if (isActive){
                    Log.d(TAG, "Result for i = $i: ${fib(i)}")
                }
            }
            Log.d(TAG, "Ending long running  calculation")
        }

        runBlocking {
            delay(300L)
            Log.d(TAG, "Canceled job!")//
            job.cancel()
        }

    }

    // fibonachi
    fun fib(n: Int): Long {
        return if (n == 0) 0
        else if (n == 1) 1
        else fib(n - 1) + fib(n - 2)
    }
}


// Example2
// cancel
// if the job takes more than 5 seconds will cancel the job
//        val job = GlobalScope.launch(Dispatchers.Default) {
//            // will repeat 5 times whatever we put on this block
//            repeat(10){
//                Log.d(TAG, "Coroutine  is still working...")
//                delay(1000L)
//            }
//        }

//        runBlocking {
//            delay(5000L)
//            job.cancel()
//            Log.d(TAG, "Main thread is continuing...")
//        }

//        //Example1
//        //join function
//        val job = GlobalScope.launch(Dispatchers.Default) {
//            // will repeat 5 times whatever we put on this block
//            repeat(10){
//                Log.d(TAG, "Coroutine  is still working...")
//                delay(1000L)
//            }
//        }


//        //it block the Main thread until the job is finishe
//        runBlocking {
//            job.join()
//            Log.d(TAG, "Main thread is continuing...")
//        }