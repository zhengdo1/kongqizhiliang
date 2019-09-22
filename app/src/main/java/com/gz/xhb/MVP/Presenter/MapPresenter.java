package com.gz.xhb.MVP.Presenter;

import android.os.Handler;

import com.gz.xhb.MVP.Model.API.cache.CacheProviders;
import com.gz.xhb.MVP.Model.Entity.BaseArrayVO;
import com.gz.xhb.MVP.Model.Entity.BaseVO;
import com.gz.xhb.MVP.Model.Entity.Map;
import com.gz.xhb.MVP.Model.Entity.User;
import com.gz.xhb.MVP.Model.MapBiz;
import com.gz.xhb.MVP.Model.ServiceManager;
import com.gz.xhb.MVP.View.MapInfoView;
import com.gz.xhb.util.MyObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;


/**
 * Created by xjj on 2018/5/21.
 */

public class MapPresenter {
    MapInfoView mapInfoView;
    MapBiz biz;

    //    @Inject
    public MapPresenter(MapInfoView mapInfoView) {
        this.mapInfoView = mapInfoView;
        biz = new MapBiz();
    }

    public void getList() {
        io.reactivex.Observable<BaseArrayVO<Map>> mapInfoList = new ServiceManager().getMapPsList();

        CacheProviders.getCache()
                .getMapPsList(mapInfoList,new DynamicKey("map"),new EvictDynamicKey(false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<BaseArrayVO<Map>>() {
                    @Override
                    public void onSuccess(BaseArrayVO<Map> mapBaseArrayVO) {
                        mapInfoView.showPsDot(mapBaseArrayVO.getData());
                    }

                    @Override
                    public void onFail(Throwable e) {
                        mapInfoView.showError();
                    }
                });
    }
}

