package com.example.juslt.serviceipc

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.support.annotation.RequiresApi
import android.support.v4.app.TaskStackBuilder
import android.util.Log

/**
 * Created by Juslt on 2019/3/29
 * 由于后台服务优先级相对比较低，当系统出现内存不足的情况下，它就有可能会被回收掉，
 * 所以前台服务就是来弥补这个缺点的，它可以一直保持运行状态而不被系统回收。
 * 例如：墨迹天气在状态栏中的天气预报
 */
class ForegroundService: Service(){
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate() {
        super.onCreate()
        Log.e("===","foregroundService--onCreate")
        showNotification()
    }

    //启动前台通知
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun showNotification() {
        //创建通知详细信息
        val mBuilder = Notification.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("2019/3/29")
            .setContentText("今天沙尘暴，12级大风")
        //创建点击通知跳转Intent
        val intent = Intent(this,MainActivity::class.java)
        //创建任务栈Builder
        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(intent)
        val pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
        //设置跳转Intent到通知
        mBuilder.setContentIntent(pendingIntent)
        //获取通知服务
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //构建通知
        val notification = mBuilder.build()
        //显示通知
        nm.notify(0,notification)
        //启动为前台服务
        startForeground(0,notification)
    }

}