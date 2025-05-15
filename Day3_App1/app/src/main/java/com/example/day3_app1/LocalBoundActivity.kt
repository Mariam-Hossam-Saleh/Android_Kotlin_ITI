package com.example.day3_app1

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LocalBoundActivity : AppCompatActivity() {

    lateinit var myService: BoundService
    var isBound : Boolean = false
    private lateinit var dateTextView : TextView
    private lateinit var showTimeBtn : Button

    private var myConnection : ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as BoundService.MyLocalBinder
            myService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_bound)
        dateTextView = findViewById(R.id.textView)
        showTimeBtn = findViewById(R.id.btnShowTime)

        showTimeBtn.setOnClickListener {
            if(isBound) {
                showTime(it)
            }
        }

        val intent = Intent(this, BoundService::class.java)
        bindService(intent,myConnection, Context.BIND_AUTO_CREATE)
    }

    private fun showTime(view : View) {
        val currentTime : String = myService.getCurrentTime()
        Log.i("LocalBoundActivity", "showTime: $currentTime")
        dateTextView.text = currentTime
    }
}