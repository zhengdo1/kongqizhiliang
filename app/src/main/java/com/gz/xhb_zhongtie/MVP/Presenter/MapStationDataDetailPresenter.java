package com.gz.xhb_zhongtie.MVP.Presenter;

import com.gz.xhb_zhongtie.MVP.Model.API.cache.CacheProviders;
import com.gz.xhb_zhongtie.MVP.Model.Entity.BaseArrayVO;
import com.gz.xhb_zhongtie.MVP.Model.Entity.MapStationDataDetail;
import com.gz.xhb_zhongtie.MVP.Model.ServiceManager;
import com.gz.xhb_zhongtie.MVP.Model.StationDataDetailBiz;
import com.gz.xhb_zhongtie.MVP.View.StationDataDetailView;
import com.gz.xhb_zhongtie.util.MyObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import okhttp3.ResponseBody;


/**
 * Created by xjj on 2018/5/21.
 */

public class MapStationDataDetailPresenter {
    StationDataDetailView stationDataDetailView;
    StationDataDetailBiz biz;

    //    @Inject
    public MapStationDataDetailPresenter(StationDataDetailView stationDataDetailView) {
        this.stationDataDetailView = stationDataDetailView;
        biz = new StationDataDetailBiz();
    }

    public void getMapStationDataDetail(String pscode, String outputcode, String istoutput) {
        io.reactivex.Observable<BaseArrayVO<MapStationDataDetail>> mapInfoList = new ServiceManager().getMapStationDataDetail(pscode, outputcode, istoutput);

        CacheProviders.getCache()
                .getMapStaionDataDetail(mapInfoList, new DynamicKey("mapStationDataDetail"+ pscode + outputcode + istoutput), new EvictDynamicKey(false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<BaseArrayVO<MapStationDataDetail>>() {
                    @Override
                    public void onSuccess(BaseArrayVO<MapStationDataDetail> mapStationDataDetailBaseArrayVO) {
                        stationDataDetailView.showData2(mapStationDataDetailBaseArrayVO.getData());
                    }

                    @Override
                    public void onFail(Throwable e) {
                        stationDataDetailView.showError();
                    }
                });
    }
}

