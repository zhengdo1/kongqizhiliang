package com.gz.xhb_zhongtie.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by z on 2016/7/7.
 */
public class ToastTool {
    private static Toast toast;
    private static Boolean mToastbool = true;
    private static int WaitTime = (int) (2.5 * 1000);
    /**
     * @param context The Class's context , where  want to use this tool
     * @param message The message will be show
     */
    public static void ShowLongToast(final Context context, final String message) {
//        Activity activity = (Activity) context;
//        Snackbar.make(activity.getCurrentFocus(), message, Snackbar.LENGTH_LONG)
//                .setAction("Action", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(
//                                context,
//                                message,
//                                Toast.LENGTH_SHORT).show();
//                    }
//                }).show();
        toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        Show();
    }

    public static void ShowShortToast(final Context context, final String message) {
//        Activity activity = (Activity) context;
//        Snackbar.make(activity.getCurrentFocus(), message, Snackbar.LENGTH_LONG)
//                .setAction("Action", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(
//                                context,
//                                message,
//                                Toast.LENGTH_SHORT).show();
//                    }
//                }).show();
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        Show();
    }

//  1.5秒以内再次点击，不会Toast
    private static void Show() {
        if (mToastbool == true) {
            toast.show();
            mToastbool = false;
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(WaitTime);
                        mToastbool = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } else {
        }

    }
}
