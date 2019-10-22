package com.gz.xhb.MVP.Model.API.cache;


import com.amap.api.maps.model.Poi;
import com.gz.xhb.MVP.Model.Entity.BaseArrayVO;
import com.gz.xhb.MVP.Model.Entity.BaseVO;
import com.gz.xhb.MVP.Model.Entity.Map;
import com.gz.xhb.MVP.Model.Entity.MapStationDataDetail;
import com.gz.xhb.MVP.Model.Entity.PortInfo;
import com.gz.xhb.MVP.Model.Entity.PsBaseInfo;
import com.gz.xhb.MVP.Model.Entity.PsListInfo;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;
import okhttp3.ResponseBody;

/**
 * Created by QingMei on 2017/7/10.
 * desc:
 */

public interface IXHBCacheProviders {

    /**
     * LifeCache设置缓存过期时间. 如果没有设置@LifeCache , 数据将被永久缓存理除非你使用了 EvictProvider,EvictDynamicKey or EvictDynamicKeyGroup .
     * @param user
     * @param userName 驱逐与一个特定的键使用EvictDynamicKey相关的数据。比如分页，排序或筛选要求
     * @param evictDynamicKey   可以明确地清理指定的数据 DynamicKey.
     * @return
     */
    @LifeCache(duration = 0,timeUnit = TimeUnit.MINUTES)
    Observable<BaseArrayVO<Map>> getMapPsList(Observable<BaseArrayVO<Map>> psList, DynamicKey keyAndNumber, EvictDynamicKey evictDynamicKey);


    @LifeCache(duration = 0,timeUnit = TimeUnit.MINUTES)
    Observable<PsListInfo> getPsList(Observable<PsListInfo> observable, DynamicKey keyAndNumber, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 0,timeUnit = TimeUnit.MINUTES)
    Observable<PsBaseInfo> getPsBaseInfo(Observable<PsBaseInfo> psBaseInfoObservable, DynamicKey keyAndNumber, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 0,timeUnit = TimeUnit.MINUTES)
    Observable<PortInfo> getPsPortInfoList(Observable<PortInfo> portInfoObservable, DynamicKey keyAndNumber, EvictDynamicKey evictDynamicKey);


    @LifeCache(duration = 0,timeUnit = TimeUnit.MINUTES)
    Observable<ResponseBody> getOnlineData(Observable<ResponseBody> psList, DynamicKey keyAndNumber, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 0,timeUnit = TimeUnit.MINUTES)
    Observable<ResponseBody> getAlarmData100(Observable<ResponseBody> observable, DynamicKey keyAndNumber, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 0,timeUnit = TimeUnit.MINUTES)
    Observable<ResponseBody> getAlarmData80(Observable<ResponseBody> observable, DynamicKey keyAndNumber, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 0,timeUnit = TimeUnit.MINUTES)
    Observable<BaseArrayVO<MapStationDataDetail>> getMapStaionDataDetail(Observable<BaseArrayVO<MapStationDataDetail>> observable, DynamicKey keyAndNumber, EvictDynamicKey evictDynamicKey);
}
