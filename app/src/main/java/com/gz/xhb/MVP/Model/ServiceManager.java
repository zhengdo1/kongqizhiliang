package com.gz.xhb.MVP.Model;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.gz.xhb.MVP.Model.API.service.ServerInterface;
import com.gz.xhb.MVP.Model.Entity.BaseArrayVO;
import com.gz.xhb.MVP.Model.Entity.Map;
import com.gz.xhb.MVP.Model.Entity.MapStationDataDetail;
import com.gz.xhb.MVP.Model.Entity.PortInfo;
import com.gz.xhb.MVP.Model.Entity.PsBaseInfo;
import com.gz.xhb.MVP.Model.Entity.PsListInfo;
import com.gz.xhb.MVP.Model.Entity.User;
import com.gz.xhb.MyApplication.MyApplication;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */

public class ServiceManager {

    private static ServerInterface service;

    public ServiceManager() {
        init();
    }

    private void init() {
        if (service == null) {
            ClearableCookieJar cookieJar =
                    new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApplication.getContext()));

            OkHttpClient client = new OkHttpClient()
                    .newBuilder()
                    .cookieJar(cookieJar)
//                .addInterceptor(interceptor)
                    .build();

            service = new Retrofit.Builder()
//                    .baseUrl("http://183.196.222.139:88/")
                    .baseUrl("http://221.195.199.6:802/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()
                    .create(ServerInterface.class);
        }

    }

    public Observable<User> login(String LoginID, String UserPassword) {
        return service.login(LoginID, UserPassword);
    }

    public Observable<BaseArrayVO<Map>> getMapPsList() {
        return service.getMapPsList();
    }

    public Observable<PsListInfo> getPsList(String keyword, String pstype) {
        return service.getPsList(keyword, pstype);
    }

    public Observable<PsBaseInfo> getPsBaseInfo(String pscode) {
        return service.getPsBaseInfo(pscode);
    }

    public Observable<PortInfo> getPsPortList(String pscode, String outputtype) {
        return service.getPsPortList(pscode, outputtype);
    }

    public Observable<ResponseBody> getOnlineData(String pscode, String outputcode, String datatype, String outputtype, String begintime, String endtime, String pollutantcode) {
        return service.getOnlineData(pscode, outputcode, datatype, outputtype, begintime, endtime, pollutantcode);
    }


    public Observable<ResponseBody> getAlarmDataList100(String keyword, String outputtype, String begintime, String endtime) {
        return service.getAlarmData100(keyword, outputtype, begintime, endtime);
    }

    public Observable<ResponseBody> getAlarmDataList80(String keyword, String outputtype, String begintime, String endtime) {
        return service.getAlarmData80(keyword, outputtype, begintime, endtime);
    }

    public Observable<BaseArrayVO<MapStationDataDetail>> getMapStationDataDetail(String pscode, String outputcode, String istoutput) {
        return service.getMapStationDataDetail(pscode, outputcode, istoutput);
    }

//    public Observable<BaseArrayVO<NearPsList>> getNearPsList(RequestBody body){
//        return service.getNearPsList(body);
//    }
}
