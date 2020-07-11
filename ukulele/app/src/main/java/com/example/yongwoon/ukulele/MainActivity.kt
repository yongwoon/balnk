package com.example.yongwoon.ukulele

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SurfaceHolder.Callback {
    private var frets : Array<Float> = arrayOf(0f, 0f, 0f, 0f)
    private var strings : Array<Float> = arrayOf(0f, 0f, 0f, 0f)
    private var screenHeight: Int = 0
    private var diffY: Int = 0

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

        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenHeight = size.y

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
        diffY = screenHeight - height
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
//        TODO("Not yet implemented")
    }

    override fun surfaceCreated(p0: SurfaceHolder?) {
//        TODO("Not yet implemented")
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var stringNo: Int = 0
        var fretNo: Int = 0

        if(event?.action == MotionEvent.ACTION_DOWN) {
            when(event.x) {
                in (strings[0] - 200)..(strings[0] + 200) -> stringNo = 4
                in (strings[1] - 200)..(strings[1] + 200) -> stringNo = 3
                in (strings[2] - 200)..(strings[2] + 200) -> stringNo = 2
                in (strings[3] - 200)..(strings[3] + 200) -> stringNo = 1
            }
            when(event.y - diffY) {
                in 15f..(frets[0] - 15f) -> fretNo = 0
                in (frets[0] + 15f)..(frets[1] - 15f) -> fretNo = 1
                in (frets[1] + 15f)..(frets[2] - 15f) -> fretNo = 2
                in (frets[2] + 15f)..(frets[3] - 15f) -> fretNo = 3
            }
            playTone(stringNo, fretNo)
        }
        return true // 通知終わり
    }

    private fun playTone(stringNo :Int, fretNo :Int) {
        when(stringNo) {
            4 -> {
                when(fretNo) {
                    0 -> {
                        g5.seekTo(0)
                        g5.start()
                    }
                    1 -> {
                        gs5.seekTo(0)
                        gs5.start()
                    }
                    2 -> {
                        a5.seekTo(0)
                        a5.start()
                    }
                    1 -> {
                        as5.seekTo(0)
                        as5.start()
                    }
                }
            }
            3 -> {
                when(fretNo) {
                    0 -> {
                        c5.seekTo(0)
                        c5.start()
                    }
                    1 -> {
                        cs5.seekTo(0)
                        cs5.start()
                    }
                    2 -> {
                        d5.seekTo(0)
                        d5.start()
                    }
                    3 -> {
                        ds5.seekTo(0)
                        ds5.start()
                    }
                }
            }
            2 -> {
                when(fretNo) {
                    0 -> {
                        e5.seekTo(0)
                        e5.start()
                    }
                    1 -> {
                        f5.seekTo(0)
                        f5.start()
                    }
                    2 -> {
                        fs5.seekTo(0)
                        fs5.start()
                    }
                    3 -> {
                        g5.seekTo(0)
                        g5.start()
                    }
                }
            }
            1 -> {
                when(fretNo) {
                    0 -> {
                        a5.seekTo(0)
                        a5.start()
                    }
                    1 -> {
                        as5.seekTo(0)
                        as5.start()
                    }
                    2 -> {
                        b5.seekTo(0)
                        b5.start()
                    }
                    3 -> {
                        c6.seekTo(0)
                        c6.start()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        c5.release()
        cs5.release()
        d5.release()
        ds5.release()
        e5.release()
        f5.release()
        fs5.release()
        g5.release()
        gs5.release()
        a5.release()
        as5.release()
        b5.release()
        c6.release()
    }
}