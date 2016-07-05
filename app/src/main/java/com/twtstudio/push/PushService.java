package com.twtstudio.push;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by jcy on 2016/7/4.
 */

public class PushService extends Service {
    private static final String TAG = "twtPush";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"service created");
        //配置mqtt客户端
        TwtMqtt mqtt=new TwtMqtt(this);
        mqtt.setBroker("tcp://121.42.157.180:61613");
        mqtt.setTopic("twtandroid1");
        mqtt.setUserName("twtandroid3");
        mqtt.setPassword("twtandroid3");
        mqtt.setQos(1);
        mqtt.setClientId("twtandroid3");
        mqtt.init();
        mqtt.subscribe();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void sendNotification(String message)
    {
        //发出通知
        NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher).
                setContentTitle("TwtPush").setContentText(message);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(2,mBuilder.build());
    }
}
