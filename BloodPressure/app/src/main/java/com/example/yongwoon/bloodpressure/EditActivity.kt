package com.example.yongwoon.bloodpressure

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*


class EditActivity : AppCompatActivity() {
    private val tag = "BloodPressure"
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        realm = Realm.getDefaultInstance()

        saveBtn.setOnClickListener{
            var max: Long = 0
            var min: Long = 0
            var pulse: Long = 0

            if(!maxEdit.text.isNullOrEmpty()) {
                max = maxEdit.text.toString().toLong()
            }

            if(!minEdit.text.isNullOrEmpty()) {
                min = minEdit.text.toString().toLong()
            }

            if(!pulseEdit.text.isNullOrEmpty()) {
                pulse = pulseEdit.text.toString().toLong()
            }

            realm.executeTransaction {
                val maxId = realm.where<BloodPress>().max("id")
                val nextId = (maxId?.toLong() ?: 0L) + 1L
                val bloodPress = realm.createObject<BloodPress>(nextId)
                bloodPress.dateTime = Date()
                bloodPress.max = max
                bloodPress.min = min
                bloodPress.pulse = pulse
            }

            Toast.makeText(applicationContext, "保存しました", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
