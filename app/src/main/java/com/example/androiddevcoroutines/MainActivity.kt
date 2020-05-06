package com.example.androiddevcoroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example2
        // lifecycleScope coroutine will be destroyed when MainActivity destroyed
        btnStartActivity.setOnClickListener {
            lifecycleScope.launch {
                while (true) {
                    delay(1000L)
                    Log.d(TAG, "Still running ...")
                }
            }


            GlobalScope.launch {
                delay(5000L)
                Intent(this@MainActivity,SecondActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }

    }

}

// GlobalScope . . is a bad practice

//// Example1
//// Even the MainActivity is destroyed the coroutine is still running which is bad
//btnStartActivity.setOnClickListener {
//    GlobalScope.launch {
//        while (true) {
//            delay(1000L)
//            Log.d(TAG, "Still running ...")
//        }
//    }
//
//
//    GlobalScope.launch {
//        delay(5000L)
//        Intent(this@MainActivity,SecondActivity::class.java).also {
//            startActivity(it)
//            finish()
//        }
//    }
//}