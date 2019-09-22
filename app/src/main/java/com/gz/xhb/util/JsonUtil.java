package com.gz.xhb.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zdj on 2018/6/30.
 */
public class JsonUtil {
    public static List<Map<String,String>> jsonArrayToMapList(JSONArray jsonArray) {
        List<java.util.Map<String,String>> list = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Iterator iterator = jsonObject.keys();
                java.util.Map map = new HashMap<String, String>();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    String value = jsonObject.getString(key);
                    map.put(key, value);
                }
                list.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
