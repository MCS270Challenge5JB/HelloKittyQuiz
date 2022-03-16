package com.example.hellokittyquiz

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

private val TAG = "SummaryActivity"

class SummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_summary)
    }
}