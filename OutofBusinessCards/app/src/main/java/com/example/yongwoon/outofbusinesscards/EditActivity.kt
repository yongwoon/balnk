package com.example.yongwoon.outofbusinesscards

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val company = pref.getString("company", "")
        val postal = pref.getString("postal", "")
        val address = pref.getString("address", "")
        val tel = pref.getString("tel", "")
        val fax = pref.getString("fax", "")
        val email = pref.getString("email","")
        val url = pref.getString("url", "")
        val position = pref.getString("position", "")
        val name = pref.getString("name", "")

        companyEdit.setText(company)
        postalEdit.setText(postal)
        addressEdit.setText(address)
        telEdit.setText(tel)
        faxEdit.setText(fax)
        emailEdit.setText(email)
        urlEdit.setText(url)
        positionEdit.setText(position)
        nameEdit.setText(name)

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
