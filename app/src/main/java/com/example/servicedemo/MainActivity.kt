package com.example.servicedemo

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, MyService::class.java)
        startService(intent)


        val handler = Handler()
        val runnable = Runnable {
            val counter = getSharedPreferences("HELLO", Context.MODE_PRIVATE)
                .getString("COUNTER", "")
            tvStatus.text = counter

        }

        val timerTask = object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }
        }
        val timer = Timer()
        timer.schedule(timerTask, 0, 2000)

        btnStart.setOnClickListener {
            stopService(intent)
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}
