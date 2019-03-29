package com.example.juslt.serviceipc

import android.app.IntentService
import android.content.Intent

/**
 * Created by Juslt on 2019/3/29
 * 继承IntentService并覆写onHandlerIntent函数，在该函数中就可以执行耗时操作了
 */
class TheIntentService(serviceName:String):IntentService(serviceName){

    override fun onHandleIntent(intent: Intent?) {
        //在这里执行耗时操作
    }

}