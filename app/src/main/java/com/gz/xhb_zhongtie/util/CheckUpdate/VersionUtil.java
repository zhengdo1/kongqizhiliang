package com.gz.xhb_zhongtie.util.CheckUpdate;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


public class VersionUtil {
    //用来检测本软件版本，原理是读取androidManifest里的versionName
    public static String getVersionName(Context context) throws Exception {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        return packInfo.versionName;
    }
}
