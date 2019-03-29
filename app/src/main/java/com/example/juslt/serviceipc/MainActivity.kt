package com.example.juslt.serviceipc

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import kotlinx.android.synthetic.main.activity_main.*


/**
 * 不可交互的服务 startService(intent)
 * 可交互的服务   bindService(intent,serviceConnection,tag)
 * 前台服务 ForegroundService
 * IntentService 执行耗时操作
 *
 * 系统服务提供了很多便捷服务，可以查询Wifi、网络状态、查询电量、查询音量、查询包名、查询Application信息等等等相关多的服务
 * */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, CommonService::class.java)

        btnBindService.setOnClickListener {
            //可交互的服务
            bindService(intent, conn, Context.BIND_AUTO_CREATE)

            //不可交互的服务
            //startService(intent)
        }

        btnUnBindService.setOnClickListener {
            //解除可交互的服务
            unbindService(conn)

            //解除不可交互的服务
            //stopService(intent)
        }

        btnStartForegroundService.setOnClickListener {
            //前台服务
            val intent = Intent(this,ForegroundService::class.java)
            startService(intent)
        }


        //绑定Remote服务
        startService(Intent(this,RemoteService::class.java))
    }

    private val conn = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as CommonService.MyBinder
            binder.showToast()
        }

    }
}
