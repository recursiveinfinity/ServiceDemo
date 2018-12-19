package com.example.servicedemo

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import java.util.*

class MyService : Service() {

    private var counter = 0
    private lateinit var timerTask: TimerTask
    override fun onStartCommand(intent: Intent?,
                                flags: Int, startId: Int): Int {
        doWork()
        return Service.START_NOT_STICKY
    }

    private fun doWork() {
        timerTask = object : TimerTask(){
            override fun run() {
                getSharedPreferences("HELLO", Context.MODE_PRIVATE)
                    .edit()
                    .putString("COUNTER", counter.toString())
                    .apply()
                counter += 3
            }
        }
        val timer  = Timer()
        timer.schedule(timerTask, 0, 3000)

    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        timerTask.cancel()
        super.onDestroy()

    }
}