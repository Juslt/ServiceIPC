package com.example.juslt.serviceipc

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * Created by Juslt on 2019/3/29
 */
class RemoteService: Service(){
    override fun onBind(intent: Intent?): IBinder? {
        return stub
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("===","RemoteService启动")
        return super.onStartCommand(intent, flags, startId)
    }

    val stub = object :IMyAidlInterface.Stub(){
        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun add(num1: Int, num2: Int): Int {
            Log.i("===","从远程服务发来AIDL请求：num1->$num1,num2->$num2")
            return num1+num2
        }

    }
}