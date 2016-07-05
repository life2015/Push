package com.twtstudio.push;

import android.app.Application;
import android.content.Intent;

/**
 * Created by jcy on 2016/7/4.
 */

public class PushApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent=new Intent(this,PushService.class);
        startService(intent);
    }

}
