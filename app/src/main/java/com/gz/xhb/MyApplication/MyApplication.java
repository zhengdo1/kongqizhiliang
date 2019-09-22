package com.gz.xhb.MyApplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by z on 2016/10/12.
 */

public class MyApplication extends Application{

    private static Context context;
    private Application application;

    public MyApplication() {
        this.context=this;
    }

    public static Context getContext(){
        return context;
    }



}
