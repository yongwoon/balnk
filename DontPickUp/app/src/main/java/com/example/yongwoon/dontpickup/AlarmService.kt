package com.example.yongwoon.dontpickup

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.IBinder

class AlarmService : Service(), SensorEventListener {

    private val threshold: Float = 15f
    private var mp: MediaPlayer? = null
    private var oValue: Array<Float> = arrayOf(0f, 0f, 0f)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER){
            val speed = Math.abs(event.values[0] - oValue[0]) +
                        Math.abs(event.values[1] - oValue[1]) +
                        Math.abs(event.values[2] - oValue[2])

            if(speed > threshold) {
                mp = MediaPlayer.create(applicationContext, R.raw.voice)
                mp?.start()
            }

            oValue[0] = event.values[0]
            oValue[1] = event.values[1]
            oValue[2] = event.values[2]
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
