package com.gz.xhb.MVP.Model.API.service;


import com.gz.xhb.MVP.Model.Entity.BaseArrayVO;
import com.gz.xhb.MVP.Model.Entity.BaseVO;
import com.gz.xhb.MVP.Model.Entity.Map;
import com.gz.xhb.MVP.Model.Entity.MapStationDataDetail;
import com.gz.xhb.MVP.Model.Entity.PortInfo;
import com.gz.xhb.MVP.Model.Entity.PsBaseInfo;
import com.gz.xhb.MVP.Model.Entity.PsListInfo;
import com.gz.xhb.MVP.Model.Entity.User;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by dell on 2018/5/14.
 */

public interface ServerInterface {


    @FormUrlEncoded
    @POST("SignIn/CheckUser")
    Observable<User> login(@Field("LoginID") String LoginID, @Field("UserPassword") String UserPassword);

    @GET("Main/GetMapInfo")
    Observable<BaseArrayVO<Map>> getMapPsList();

    //根据关键字查询企业列表
    @FormUrlEncoded
    @POST("Core/GetPsList")
    Observable<PsListInfo> getPsList(@Field("keyword") String keyword,@Field("pstype") String pstype);

    @FormUrlEncoded
    @POST("Core/GetPsByCode")
    Observable<PsBaseInfo> getPsBaseInfo(@Field("pscode") String pscode);

    //根据企业ID查询企业排口列表
    @FormUrlEncoded
    @POST("Output/GetOutputListByPsCode")
    Observable<PortInfo> getPsPortList(@Field("pscode") String pscode,@Field("outputtype") String outputtype);


    //查询数据
    @FormUrlEncoded
    @POST("MonitorData/MonitorDataList_ForApp")
    Observable<ResponseBody> getOnlineData(@Field("pscode") String pscode,@Field("outputcode") String outputcode,@Field("datatype") String datatype
            ,@Field("outputtype") String outputtype,@Field("begintime") String begintime
            ,@Field("endtime") String endtime,@Field("pollutantcode") String pollutantcode);

    //超标报警数据
    @FormUrlEncoded
    @POST("Alarm/GetAlarmData100_ForApp")
    Observable<ResponseBody> getAlarmData100(@Field("keyword") String keyword,@Field("outputtype") String outputtype,@Field("begintime") String begintime
            ,@Field("endtime") String endtime);

    //超标预警数据
    @FormUrlEncoded
    @POST("Alarm/GetAlarmData80_ForApp")
    Observable<ResponseBody> getAlarmData80(@Field("keyword") String keyword,@Field("outputtype") String outputtype,@Field("begintime") String begintime
            ,@Field("endtime") String endtime);



    @GET("DataChart/ColumnRotatedSeries_ForApp")
    Observable<BaseArrayVO<MapStationDataDetail>> getMapStationDataDetail(@Query("pscode")String pscode, @Query("outputcode")String outputcode, @Query("istoutput")String istoutput);
}
