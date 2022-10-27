package com.mungyu.receivercall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.title).apply {
            val str = intent?.getStringExtra("data")
            text = str
            Log.i("MQ!", "text:$str")
        }
    }
}