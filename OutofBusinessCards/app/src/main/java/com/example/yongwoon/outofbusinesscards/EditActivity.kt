package com.example.yongwoon.outofbusinesscards

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

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

            companyEdit.setText(company)
            postalEdit.setText(postal)
            addressEdit.setText(address)
            telEdit.setText(tel)
            faxEdit.setText(fax)
            emailEdit.setText(email)
            urlEdit.setText(url)
            positionEdit.setText(position)
            nameEdit.setText(name)
        }

        saveBtn.setOnClickListener() {
            saveData()
            // Activity 終了
            finish()
        }

        cancelBtn.setOnClickListener(){
            // Activity 終了
            finish()
        }
    }

    private fun saveData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putString("company", companyEdit.text.toString())
            .putString("postal", postalEdit.text.toString())
            .putString("address", addressEdit.text.toString())
            .putString("tel", telEdit.text.toString())
            .putString("fax", faxEdit.text.toString())
            .putString("email", emailEdit.text.toString())
            .putString("url", urlEdit.text.toString())
            .putString("position", positionEdit.text.toString())
            .putString("name", nameEdit.text.toString())
            .apply()

    }
}
