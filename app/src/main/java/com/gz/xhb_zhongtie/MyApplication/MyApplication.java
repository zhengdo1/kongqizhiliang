package com.gz.xhb_zhongtie.MyApplication;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.gz.xhb_zhongtie.App.Constants;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by z on 2016/10/12.
 */

public class MyApplication extends Application {

    private static Context context;
    private Application application;

    public MyApplication() {

    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = Constants.JPUSH_CHANNEL_ID;
            String channelName = Constants.JPUSH_CHANNEL_NAME;
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(context, channelId, channelName, importance);
        }
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(Context context, String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

}
