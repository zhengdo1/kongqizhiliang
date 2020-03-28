package com.gz.xhb_zhongtie.App.Jpush;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.gz.xhb_zhongtie.Activity.LoginActivity;
import com.gz.xhb_zhongtie.App.Constants;
import com.gz.xhb_zhongtie.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AppJpushReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            Log.e(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
            Log.e(TAG, "8.0处理极光推送通知栏显示问题");
            int notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);//定义通知id
            String message = bundle.getString(JPushInterface.EXTRA_ALERT);
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String s = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Intent intent0 = new Intent(context, LoginActivity.class);
            String channelId = Constants.JPUSH_CHANNEL_ID;
            if (title != null) {
                Log.i("jpushMessage:","pushTitle:" + title);
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
                PendingIntent pi = PendingIntent.getActivity(context, 0, intent0, PendingIntent.FLAG_UPDATE_CURRENT);
                Notification notification = new NotificationCompat.Builder(context, channelId)
                        .setContentTitle(title)//设置通知栏标题
                        .setContentText(message)
                        .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                        .setSmallIcon(R.mipmap.logo)//设置通知小ICON
                        .setChannelId(channelId)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentIntent(pi)
                        .build();
                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                if (notificationManager != null) {
                    notificationManager.notify(notificationId, notification);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "极光推送出错:" + e.getMessage());
        }

    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

}
