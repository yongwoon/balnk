package com.example.yongwoon.helloworld

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
// morningBtn.setOnClickListener や greetingText.text などを関数として使うことができる
import kotlinx.android.synthetic.main.activity_main.*
import java.time.format.ResolverStyle

// AppCompatActivity を継承します。
class MainActivity : AppCompatActivity() {

    // Kotlin の関数や method の宣言です。
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 画面に表示する View をせっていします。
        // ここで指定している R.layout.activity_main とういう定数は、
        // activity_main.xml から生成された値です。
        setContentView(R.layout.activity_main)

        morningBtn.setOnClickListener {
            greetingText.text = getString(R.string.good_morning_text)
        }

        afternoonBtn.setOnClickListener {
            greetingText.text = getString(R.string.afternoon_text)
        }

        eveningBtn.setOnClickListener {
            greetingText.text = getString(R.string.evening_text)
        }
    }
}
