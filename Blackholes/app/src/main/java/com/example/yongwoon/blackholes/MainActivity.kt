package com.example.yongwoon.blackholes

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener, SurfaceHolder.Callback {
    private val tag = "Black holes"
    private val matrixSize = 16
    private val numOfBlackhole = 5 // 出現させる black home
    private val radius = 30f // ball の半径
    private val limitOfBlackhole = 100 // この変数は blackhole があまり surface の端っこに出ないための値

    private var mgValues = FloatArray(3)
    private var acValues = FloatArray(3)
    private var startTime:Long = 0
    private var surfaceWidth = 0
    private var surfaceHeight = 0
    private var ballX = 0f
    private var ballY = 0f
    private var isGoal = false
    private var isGone = false

    private val blackholesList = ArrayList<Blackhole>()

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        surfaceWidth = width
        surfaceHeight = height
        ballX = (width / 2).toFloat()
        ballY = (height - radius).toFloat()
        startTime = System.currentTimeMillis()
        bornBlackholes()
    }

    private fun bornBlackholes() {
        for( i in 1..numOfBlackhole) {
            val x :Float = (limitOfBlackhole..surfaceWidth - limitOfBlackhole).random().toFloat()
            val y :Float = (limitOfBlackhole..surfaceHeight - limitOfBlackhole).random().toFloat()
            val spped :Int = (2..11).random()
            val bh = Blackhole(x, y, spped)
            blackholesList.add(bh)

        }
    }

    private fun drawGameBoard(pitch: Int, roll :Int) {
        ballX += roll
        ballY -= pitch

        // 左右は逆から
        if (ballX < 0) {
            ballX = surfaceWidth - radius
        } else if (ballX > surfaceWidth) {
            ballX = radius
        }

        // 上はゴール、下は落ちない
        if (ballY + radius  < 0) {
            isGoal = true
        } else  if (ballY + radius > surfaceHeight) {
            ballY = surfaceHeight - radius
        }

        // 飲み込まれたか
        for(bh in blackholesList) {
            if (checkGone(bh.x, bh.y, bh.r)) {
                isGone = true
            }
        }

        val canvas = surfaceView.holder.lockCanvas()
        val paint = Paint()

        canvas.drawColor(Color.BLUE)
        paint.color = Color.BLACK
        for(bh in blackholesList) {
            canvas.drawCircle(bh.x, bh.y, bh.r, paint)
            bh.grow()
        }
        paint.color = Color.YELLOW
        if (!isGone ) {
            canvas.drawCircle(ballX, ballY, radius, paint)
        }
        if (isGoal) {
            paint.textSize = 80f
            canvas.drawText(goaled(), 10f, (surfaceHeight - 60).toFloat(), paint)
        }

        surfaceView.holder.unlockCanvasAndPost(canvas)
    }

    private fun checkGone(x0: Float, y0: Float, r: Float): Boolean {
        return (x0 - ballX) * (x0 - ballX) + (y0 - ballY) * (y0 - ballY) < r*r
    }

    private fun goaled(): String {
        // 経過時間
        val elapsedTime = System.currentTimeMillis() - startTime
        val secTime = (elapsedTime / 1000).toInt()

        return "Goal! ${secTime}秒"
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        var sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val magField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, magField, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val inR = FloatArray(matrixSize)
        val outR = FloatArray(matrixSize)
        val i = FloatArray(matrixSize)
        val orValues = FloatArray(3)

        if(event == null) return
        when(event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> acValues = event.values.clone()
            Sensor.TYPE_MAGNETIC_FIELD -> mgValues = event.values.clone()
        }

        SensorManager.getRotationMatrix(inR, i, acValues, mgValues)
        SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X, SensorManager.AXIS_Y, outR)
        SensorManager.getOrientation(outR, orValues)

        val pitch = rad2Deg(orValues[1])
        val roll = rad2Deg(orValues[2])

        Log.v(tag, "pitch${roll}")
        Log.v(tag, "roll${roll}")

        if(!isGoal && !isGone) {
            drawGameBoard(pitch, roll)
        }
    }

    private fun rad2Deg(rad: Float): Int {
        return Math.floor(Math.toDegrees(rad.toDouble())).toInt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val holder = surfaceView.holder
        holder.addCallback(this)
    }
}
