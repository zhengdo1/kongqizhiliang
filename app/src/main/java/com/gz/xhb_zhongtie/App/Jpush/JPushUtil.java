package com.gz.xhb_zhongtie.App.Jpush;

import android.content.Context;

import com.gz.xhb_zhongtie.App.Constants;
import com.gz.xhb_zhongtie.util.SharedPreferencesUtils;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * 极光推送工具类
 * Created by gui on 2017/9/20.
 */
public class JPushUtil {

    /**
     * 设置推送标签
     */
    public static void setJPushTags(Context context) {
        boolean jpushTagsSetted = false;
        try {
            jpushTagsSetted = (boolean) SharedPreferencesUtils.getParam(context, Constants.SPKEY_JPUSHTAGSETSUCCESS, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!jpushTagsSetted) {
            Set<String> set = new HashSet<>();
            set.add("zhongtie");
//            set.add("user"+SharedPreferencesUtils.getParam(context,"name","").toString());
//            JPushInterface.setTags(context, 1, set);//设置标签。每次调用至少设置一个 tag，覆盖之前的设置，不是新增。
            Set set1 = JPushInterface.filterValidTags(set);
            JPushInterface.setTags(context,1,set1);
            SharedPreferencesUtils.setParam(context, Constants.SPKEY_JPUSHTAGSETSUCCESS, true);
        }
    }

    /**
     * 设置退出登陆后的推送标签
     */
    public static void cleanJPushTags(Context context) {
        Set<String> set = new HashSet<>();
        set.add("noUser");
        JPushInterface.setTags(context, 0, set);//设置标签。每次调用至少设置一个 tag，覆盖之前的设置，不是新增。
        SharedPreferencesUtils.setParam(context, Constants.SPKEY_JPUSHTAGSETSUCCESS, false);
    }
}
