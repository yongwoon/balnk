package com.example.yongwoon.highandlow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val tag = "high and low"
    private var yourCard = 0
    private var droidCard = 0
    private var hitCount = 0
    private var loseCount = 0
    private var gameStart = false
    private var answered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        highBtn.setOnClickListener {
            if ((gameStart && !answered)) {
                highAndLow('h')
            }
        }

        lowBtn.setOnClickListener {
            if((gameStart && !answered)) {
                highAndLow('l')
            }
        }

        nextBtn.setOnClickListener {
            if(gameStart) {
                drawCard()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        hitCount = 0
        loseCount = 0
        hitText.text = getString(R.string.hit_text)
        loseText.text = getString(R.string.lose_text)
        gameStart = true
        drawCard()
    }

    private fun drawCard() {
        yourCardImage.setImageResource(R.drawable.z02)
        droidCardImage.setImageResource(R.drawable.z01)

        // IntRange.random() で乱数を静止絵
        yourCard = (1..13).random()
        Log.d(tag, "You:" + yourCard)

        when(yourCard) {
            1 -> yourCardImage.setImageResource(R.drawable.d01)
            2 -> yourCardImage.setImageResource(R.drawable.d02)
            3 -> yourCardImage.setImageResource(R.drawable.d03)
            4 -> yourCardImage.setImageResource(R.drawable.d04)
            5 -> yourCardImage.setImageResource(R.drawable.d05)
            6 -> yourCardImage.setImageResource(R.drawable.d06)
            7 -> yourCardImage.setImageResource(R.drawable.d07)
            8 -> yourCardImage.setImageResource(R.drawable.d08)
            9 -> yourCardImage.setImageResource(R.drawable.d09)
            10 -> yourCardImage.setImageResource(R.drawable.d10)
            11 -> yourCardImage.setImageResource(R.drawable.d11)
            12 -> yourCardImage.setImageResource(R.drawable.d12)
            13  -> yourCardImage.setImageResource(R.drawable.d13)
        }

        droidCard = (1..13).random()
        Log.d(tag, "droid:" + droidCard)
        answered = false
    }

    private fun highAndLow(answer: Char) {
        showDroidCard()
        answered = true
        val balance = yourCard - droidCard


        if(balance == 0) {
            // when even do nothing
        } else if ((balance > 0 && answer == 'h')) {
            hitCount++
            hitText.text = getString(R.string.hit_text) + hitCount
        } else if ((balance < 0 && answer == 'l')) {
            hitCount++
            hitText.text = getString(R.string.hit_text) + hitCount
        } else {
            loseCount++
            loseText.text = getString(R.string.lose_text) + loseCount
        }

       if(hitCount == 5){
            resultText.text = "あなたの勝ちです"
            gameStart = false
        } else if (loseCount == 5) {
           resultText.text = "あなたの負けです"
       } else {
           // do nothing
       }
    }

    private fun showDroidCard() {
        when(droidCard) {
            1 -> droidCardImage.setImageResource(R.drawable.c01)
            2 -> droidCardImage.setImageResource(R.drawable.c02)
            3 -> droidCardImage.setImageResource(R.drawable.c03)
            4 -> droidCardImage.setImageResource(R.drawable.c04)
            5 -> droidCardImage.setImageResource(R.drawable.c05)
            6 -> droidCardImage.setImageResource(R.drawable.c06)
            7 -> droidCardImage.setImageResource(R.drawable.c07)
            8 -> droidCardImage.setImageResource(R.drawable.c08)
            9 -> droidCardImage.setImageResource(R.drawable.c09)
            10 -> droidCardImage.setImageResource(R.drawable.c10)
            11 -> droidCardImage.setImageResource(R.drawable.c11)
            12 -> droidCardImage.setImageResource(R.drawable.c12)
            13  -> droidCardImage.setImageResource(R.drawable.c13)
        }
    }
}
