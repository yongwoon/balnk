package com.example.yongwoon.orientationget

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), SensorEventListener {
    private val matrixSize = 16
    private var mgValues = FloatArray(3)
    private var acValues = FloatArray(3)

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        val inR = FloatArray(matrixSize)
        val outR = FloatArray(matrixSize)
        val I = FloatArray(matrixSize)
        val orValues = FloatArray(3)

        if (event == null) return

        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> acValues = event.values.clone()
            Sensor.TYPE_MAGNETIC_FIELD -> mgValues = event.values.clone()
        }

        SensorManager.getRotationMatrix(inR, I, acValues, mgValues)

        // 携帯を水平に持ち、activity は portrait
        // SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X, SensorManager.AXIS_Y, outR)
        SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X, SensorManager.AXIS_Y, outR)
        SensorManager.getOrientation(outR, orValues)

        val strBuild = StringBuilder()
        strBuild.append("方位角(アジマス) :")
        strBuild.append(rad2Deg(orValues[0]))
        strBuild.append("\n")

        strBuild.append("傾斜角(ピッチ)")
        strBuild.append(rad2Deg(orValues[1]))
        strBuild.append("\n")

        strBuild.append("回転角(ロール)")
        strBuild.append(rad2Deg(orValues[2]))
        strBuild.append("\n")

        txt01.text = strBuild.toString()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public override fun onResume() {
        super.onResume()

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val magField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, magField, SensorManager.SENSOR_DELAY_NORMAL)
    }

    private fun rad2Deg(rad: Float): Int {
        return Math.floor(Math.toDegrees(rad.toDouble())).toInt()
    }

}
