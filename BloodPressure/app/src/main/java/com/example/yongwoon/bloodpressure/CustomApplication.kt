package com.example.yongwoon.bloodpressure

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

// application class の onCreate() method は MainActivity よりも先に呼び出されますが、作っただけでは実行されません。
// CustomApplication class がアプリ起動時に実行されるようにするには、
// AndroidManifest.xml の Application tag に追記が必要です。
class CustomApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)
    }
}