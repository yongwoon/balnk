package com.example.yongwoon.memomemotime

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

// application class の　onCreate() method は MainActivity よりも先に呼び出されますが、作っただけでは実行
// されません。この CustomApplication class がアプリ起動時に実行されるようにするには, AndroidManifest.xml
// の　application tag に追記がする必要があります。
class CustomApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()

        Realm.setDefaultConfiguration(config)
    }
}