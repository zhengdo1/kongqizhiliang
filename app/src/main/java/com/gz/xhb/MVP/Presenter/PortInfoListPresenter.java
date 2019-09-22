package com.gz.xhb.MVP.Presenter;

import com.gz.xhb.MVP.Model.API.cache.CacheProviders;
import com.gz.xhb.MVP.Model.Entity.BaseArrayVO;
import com.gz.xhb.MVP.Model.Entity.Map;
import com.gz.xhb.MVP.Model.Entity.PortInfo;
import com.gz.xhb.MVP.Model.Entity.PortInfoData;
import com.gz.xhb.MVP.Model.PortInfoBiz;
import com.gz.xhb.MVP.Model.ServiceManager;
import com.gz.xhb.MVP.View.PortInfoListView;
import com.gz.xhb.MVP.View.PortInfoListView;
import com.gz.xhb.util.MyObserver;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;


/**
 * Created by xjj on 2018/5/21.
 */

public class PortInfoListPresenter {
    PortInfoListView portInfoListView;
    PortInfoBiz biz;

    //    @Inject
    public PortInfoListPresenter(PortInfoListView portInfoListView) {
        this.portInfoListView = portInfoListView;
        biz = new PortInfoBiz();
    }

    public void getList(String psCode, String outputtype) {
        io.reactivex.Observable<PortInfo> portInfoObservable = new ServiceManager().getPsPortList(psCode,outputtype);

        CacheProviders.getCache()
                .getPsPortInfoList(portInfoObservable, new DynamicKey("getPortInfoList"+psCode+outputtype), new EvictDynamicKey(false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<PortInfo>() {
                    @Override
                    public void onSuccess(PortInfo portInfo) {
                        List<PortInfoData> portInfoDataList = new ArrayList<>();
                        List<PortInfoData> list = portInfo.getData();
                        if (list != null && list.size() != 0) {
                            for (PortInfoData portInfoData : list) {
//                                if (portInfoData.getOutputtype().equals(type)) {
                                    portInfoDataList.add(portInfoData);
//                                }
                            }
                            portInfoListView.showList(portInfoDataList);
                        } else {
                            portInfoListView.showEmpty();
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {
                        portInfoListView.showError();
                    }
                });
    }
}

