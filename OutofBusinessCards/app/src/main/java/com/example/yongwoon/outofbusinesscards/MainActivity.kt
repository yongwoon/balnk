package com.example.yongwoon.outofbusinesscards

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        PreferenceManager.getDefaultSharedPreferences(this).apply {
            val company = getString("company", "")
            val postal = getString("postal", "")
            val address = getString("address", "")
            val tel = getString("tel", "")
            val fax = getString("fax", "")
            val email = getString("email","")
            val url = getString("url", "")
            val position = getString("position", "")
            val name = getString("name", "")

            companyText.text = company
            postalText.text = postal
            addressText.text = address
            telText.text = "tel:" + tel
            faxText.text = "fax:" + fax
            emailText.text = email
            urlText.text = url
            positionText.text = position
            nameText.text = name
        }
    }
}
