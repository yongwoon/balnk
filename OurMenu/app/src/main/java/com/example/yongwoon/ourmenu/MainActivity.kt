package com.example.yongwoon.ourmenu

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerForContextMenu(menuImage)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.home -> {
                menuImage.setImageResource(R.drawable.okasan)
                menuText.text = ""

                return true
            }
            R.id.greencurry -> {
                menuImage.setImageResource(R.drawable.greencurry)
                menuText.text = getString(R.string.greencurry_text)

                return true
            }
            R.id.beefcurry -> {
                menuImage.setImageResource(R.drawable.beefcurry)
                menuText.text = getString(R.string.beefcurry_text)

                return true
            }
            R.id.tamoricurry-> {
                menuImage.setImageResource(R.drawable.tamoricurry)
                menuText.text = getString(R.string.tamoricurry_text)

                return true
            }
            R.id.katsucurry-> {
                menuImage.setImageResource(R.drawable.katsucurry)
                menuText.text = getString(R.string.katsucurry_text)

                return true
            }
            R.id.soba1-> {
                menuImage.setImageResource(R.drawable.soba1)
                menuText.text = getString(R.string.soba1_text)

                return true
            }
            R.id.soba2-> {
                menuImage.setImageResource(R.drawable.soba2)
                menuText.text = getString(R.string.soba2_text)

                return true
            }
            R.id.ramen1-> {
                menuImage.setImageResource(R.drawable.ramen1)
                menuText.text = getString(R.string.ramen1_text)

                return true
            }
            R.id.ramen2-> {
                menuImage.setImageResource(R.drawable.ramen2)
                menuText.text = getString(R.string.ramen2_text)

                return true
            }
            R.id.nabeyaki-> {
                menuImage.setImageResource(R.drawable.nabeyaki)
                menuText.text = getString(R.string.nabeyaki_text)

                return true
            }
            R.id.udon-> {
                menuImage.setImageResource(R.drawable.udon)
                menuText.text = getString(R.string.udon_text)

                return true
            }
            R.id.hiyashi-> {
                menuImage.setImageResource(R.drawable.hiyashi)
                menuText.text = getString(R.string.hiyashi_text)

                return true
            }
            R.id.oden-> {
                menuImage.setImageResource(R.drawable.oden)
                menuText.text = getString(R.string.oden_text)

                return true
            }
            R.id.osyarenabe-> {
                menuImage.setImageResource(R.drawable.osyarenabe)
                menuText.text = getString(R.string.osyarenabe_text)

                return true
            }
            R.id.beefbowl-> {
                menuImage.setImageResource(R.drawable.beefbowl)
                menuText.text = getString(R.string.beefbowl_text)

                return true
            }
            R.id.peperoncino-> {
                menuImage.setImageResource(R.drawable.peperoncino)
                menuText.text = getString(R.string.peperoncino_text)

                return true
            }
            R.id.hiroshima-> {
                menuImage.setImageResource(R.drawable.hiroshima)
                menuText.text = getString(R.string.hiroshima_text)

                return true
            }
            R.id.hayashi-> {
                menuImage.setImageResource(R.drawable.hayashi)
                menuText.text = getString(R.string.hayashi_text)

                return true
            }
            R.id.ankake-> {
                menuImage.setImageResource(R.drawable.ankake)
                menuText.text = getString(R.string.ankake_text)

                return true
            }
            R.id.baigai-> {
                menuImage.setImageResource(R.drawable.baigai)
                menuText.text = getString(R.string.baigai_text)

                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if(menuText.text.isNotEmpty()){
            menuInflater.inflate(R.menu.context, menu)
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.mail -> {
                val subject = getString(R.string.app_name)
                val text = "${menuText.text}が食べたい!"
                val uri = Uri.fromParts("mailto", "yonyon.japan@gmail.com", null)
                val intent = Intent(Intent.ACTION_SENDTO, uri)

                intent.putExtra(Intent.EXTRA_SUBJECT, subject)
                intent.putExtra(Intent.EXTRA_TEXT, text)

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
                return true
            }

            R.id.sms -> {
                val text = "${menuText.text}が食べたい"
                val uri = Uri.fromParts("smsto", "999999999", null)
                val intent = Intent(Intent.ACTION_SENDTO, uri)
                intent.putExtra("sms_body", text)

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }

                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}
