package com.example.yongwoon.batapikaever

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startBtn.setOnClickListener{
            val intent = Intent(this, TorchOnService::class.java)
            startService(intent)
        }

        stopBtn.setOnClickListener{
            val intent = Intent(this, TorchOnService::class.java)
            stopService(intent)
        }
    }
}
