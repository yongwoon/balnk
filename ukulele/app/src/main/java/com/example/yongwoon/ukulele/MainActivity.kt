package com.example.yongwoon.ukulele

import android.graphics.Color
import android.graphics.Paint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SurfaceHolder.Callback {
    private var frets : Array<Float> = arrayOf(0f, 0f, 0f, 0f)
    private var strings : Array<Float> = arrayOf(0f, 0f, 0f, 0f)

    // 13 sound
    private var c5: MediaPlayer = MediaPlayer()
    private var cs5: MediaPlayer = MediaPlayer()
    private var d5: MediaPlayer = MediaPlayer()
    private var ds5: MediaPlayer = MediaPlayer()
    private var e5: MediaPlayer = MediaPlayer()
    private var f5: MediaPlayer = MediaPlayer()
    private var fs5: MediaPlayer = MediaPlayer()
    private var g5: MediaPlayer = MediaPlayer()
    private var gs5: MediaPlayer = MediaPlayer()
    private var a5: MediaPlayer = MediaPlayer()
    private var as5: MediaPlayer = MediaPlayer()
    private var b5: MediaPlayer = MediaPlayer()
    private var c6: MediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val holder = surfaceView.holder
        holder.addCallback(this)

        c5 = MediaPlayer.create(this, R.raw.c5)
        cs5 = MediaPlayer.create(this, R.raw.cs5)
        d5 = MediaPlayer.create(this, R.raw.d5)
        ds5 = MediaPlayer.create(this, R.raw.ds5)
        e5 = MediaPlayer.create(this, R.raw.e5)
        f5 = MediaPlayer.create(this, R.raw.f5)
        fs5 = MediaPlayer.create(this, R.raw.fs5)
        g5 = MediaPlayer.create(this, R.raw.g5)
        gs5 = MediaPlayer.create(this, R.raw.gs5)
        a5 = MediaPlayer.create(this, R.raw.a5)
        as5 = MediaPlayer.create(this, R.raw.as5)
        b5 = MediaPlayer.create(this, R.raw.b5)
        c6 = MediaPlayer.create(this, R.raw.c6)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        val canvas = surfaceView.holder.lockCanvas()
        canvas.drawColor(Color.rgb(115, 66, 41)) // 茶色

        val widthFret:Float = (height / 4).toFloat()
        val paint = Paint()
        paint.color = Color.rgb(42, 42, 42) // 鉄黒
        paint.strokeWidth = 30f
        for(i in 1..4) {
            canvas.drawLine(0f, widthFret * i, width.toFloat(), widthFret * i, paint)
        }

        paint.color = Color.BLACK
        canvas.drawRect(0f, 0f, width.toFloat(), widthFret * 1, paint)

        val margin = 200f
        val stringInterval : Float = ((width - (margin * 2)) / 3).toFloat()
        paint.color = Color.LTGRAY
        paint.strokeWidth = 20f
        for(i in 0..3) {
            canvas.drawLine(margin + stringInterval * i, 0f, margin + stringInterval * i, height.toFloat(), paint)
            strings[i] = margin + stringInterval * i
        }

        surfaceView.holder.unlockCanvasAndPost(canvas)
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
        TODO("Not yet implemented")
    }

    override fun surfaceCreated(p0: SurfaceHolder?) {
        TODO("Not yet implemented")
    }
}