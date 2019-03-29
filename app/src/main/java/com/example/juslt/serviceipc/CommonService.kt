package com.example.juslt.serviceipc

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 * Created by Juslt on 2019/3/29
 * 普通的服务
 */

class CommonService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return MyBinder()
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("===","onCreate")
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("===","onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("===","onDestroy")
    }

    class MyBinder : Binder() {
        fun showToast(){
            Log.e("===","showToast")
        }
    }
}