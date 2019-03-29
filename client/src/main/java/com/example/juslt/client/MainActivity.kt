package com.example.juslt.client

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.example.juslt.serviceipc.IMyAidlInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var myAidlInterface:IMyAidlInterface ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //绑定服务
        val intent = Intent()
        intent.component = ComponentName("com.example.juslt.serviceipc", "com.example.juslt.serviceipc.RemoteService")
        bindService(intent, conn, Context.BIND_AUTO_CREATE)

        //调用远程服务
        btn_add.setOnClickListener {
            val result = myAidlInterface?.add(1,2)
            Log.i("===", "从服务端调用成功的结果：$result")
        }
    }

    //服务回调
    private val conn = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            myAidlInterface=null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            myAidlInterface =  IMyAidlInterface.Stub.asInterface(service)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(conn)
    }
}
