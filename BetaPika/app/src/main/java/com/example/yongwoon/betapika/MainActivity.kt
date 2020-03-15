package com.example.yongwoon.betapika

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity(), SensorEventListener {
    // 閾値
    private val threshold: Float = 10f
    // 以前の加速度センサーの z 軸の値を記憶
    private var oldValue: Float = 0f
    private lateinit var cameraManager: CameraManager
    // 마지막에 ? 를 붙이면 null 허용
    private var cameraID: String? = null
    private var lightOn: Boolean = false

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val zDiff = Math.abs(event.values[2] - oldValue)
            if (zDiff > threshold) {
                torchOn()
            }
            oldValue = event.values[2]
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        cameraManager.registerTorchCallback(object: CameraManager.TorchCallback(){
            // touch mode が変更された時の処理
            override fun onTorchModeChanged(cameraId: String, enabled: Boolean){
                super.onTorchModeChanged(cameraId, enabled)
                cameraID = cameraId
                lightOn = enabled
            }
        }, Handler())
    }

    override fun onResume() {
        super.onResume()
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelermeter = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, accelermeter, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(this)

        if(cameraID != null){
            try {
                if(lightOn) {
                    cameraManager.setTorchMode(cameraID, false)
                }
            } catch (e: CameraAccessException) {
                e.printStackTrace()
            }
        }
    }

    private fun torchOn() {
        if(cameraID != null) {
            try {
                if(!lightOn) {
                    cameraManager.setTorchMode(cameraID, true)
                } else {
                    cameraManager.setTorchMode(cameraID, false)
                }
            } catch(e: CameraAccessException) {
                e.printStackTrace()
            }
        }
    }

}
