package com.gz.xhb_zhongtie.MVP.Presenter;

import android.util.Log;

import com.gz.xhb_zhongtie.MVP.Model.API.cache.CacheProviders;
import com.gz.xhb_zhongtie.MVP.Model.Entity.PortInfoData;
import com.gz.xhb_zhongtie.MVP.Model.ServiceManager;
import com.gz.xhb_zhongtie.MVP.View.AlarmDataListView;
import com.gz.xhb_zhongtie.util.MyObserver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import okhttp3.ResponseBody;


/**
 * Created by xjj on 2018/5/21.
 */

public class AlarmDataListPresenter {
    AlarmDataListView alarmDataListView;
    String testData = "{\n" +
            "\t\"total\": 2,\n" +
            "\t\"rows\": [{\n" +
            "\t\t\"企业名称\": \"A公司\",\n" +
            "\t\t\"监控点名称\": \"污水排口1\",\n" +
            "\t\t\"监测时间\": \"2018-06-27 01:00\",\n" +
            "\t\t\"污染因子\": \"COD\",\n" +
            "\"排放标准(mg/L)\": \"100\",\n" +
            "\t\t\"浓度均值(mg/L)\": \"110\",\n" +
            "\t\t\"超标倍数\": \"1.1\"\n" +
            "\t}, {\n" +
            "\t\t\"企业名称\": \"B公司\",\n" +
            "\t\t\"监控点名称\": \"污水排口2\",\n" +
            "\t\t\"监测时间\": \"2018-06-27 01:00\",\n" +
            "\t\t\"污染因子\": \"COD\",\n" +
            "\"排放标准(mg/L)\": \"100\",\n" +
            "\t\t\"浓度均值(mg/L)\": \"120\",\n" +
            "\t\t\"超标倍数\": \"1.2\"\n" +
            "\t}]\n" +
            "}\n";

    //    @Inject
    public AlarmDataListPresenter(AlarmDataListView alarmDataListView) {
        this.alarmDataListView = alarmDataListView;
    }

    public void getAlarmDataList100(String keyword, String outputtype, String begintime
            , String endtime) {
        io.reactivex.Observable<ResponseBody> observable = new ServiceManager().getAlarmDataList100(keyword, outputtype, begintime
                , endtime);

        CacheProviders.getCache()
                .getAlarmData100(observable, new DynamicKey("getAlarmDataList100" + keyword + outputtype + begintime + endtime), new EvictDynamicKey(false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        List<JSONObject> list = new ArrayList<>();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            JSONArray jsonArray = jsonObject.getJSONArray("rows");
                            for(int i=0;i<jsonArray.length();i++){
                                list.add((JSONObject) jsonArray.get(i));
                            }
                            alarmDataListView.showList(list);
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {
                        alarmDataListView.showError();
                    }
                });
    }

    public void getAlarmDataList80(String keyword, String outputtype, String begintime
            , String endtime) {
        io.reactivex.Observable<ResponseBody> observable = new ServiceManager().getAlarmDataList80(keyword, outputtype, begintime
                , endtime);

        CacheProviders.getCache()
                .getAlarmData80(observable, new DynamicKey("getAlarmDataList80" + keyword + outputtype + begintime + endtime), new EvictDynamicKey(false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        List<JSONObject> list = new ArrayList<>();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            JSONArray jsonArray = jsonObject.getJSONArray("rows");
                            for(int i=0;i<jsonArray.length();i++){
                                list.add((JSONObject) jsonArray.get(i));
                            }
                            alarmDataListView.showList(list);
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {
                        alarmDataListView.showError();
                    }
                });
    }

    public void getTestData() {
        List<JSONObject> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(testData);
            JSONArray jsonArray = jsonObject.getJSONArray("rows");
            for(int i=0;i<jsonArray.length();i++){
                list.add((JSONObject) jsonArray.get(i));
            }
            alarmDataListView.showList(list);
        } catch (Exception e) {
        }
    }


}

