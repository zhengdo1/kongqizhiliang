package com.gz.xhb.MVP.Presenter;

import com.gz.xhb.MVP.Model.API.cache.CacheProviders;
import com.gz.xhb.MVP.Model.Entity.BaseArrayVO;
import com.gz.xhb.MVP.Model.Entity.PsListInfo;
import com.gz.xhb.MVP.Model.PsListBiz;
import com.gz.xhb.MVP.Model.ServiceManager;
import com.gz.xhb.MVP.View.PsListView;
import com.gz.xhb.util.MyObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;


/**
 * Created by xjj on 2018/5/21.
 */

public class PsListPresenter {
    PsListView psListView;
    PsListBiz biz;

    //    @Inject
    public PsListPresenter(PsListView psListView) {
        this.psListView = psListView;
        biz = new PsListBiz();
    }

    public void getList(String keyword,String pstype) {
        io.reactivex.Observable<PsListInfo> mapInfoList = new ServiceManager().getPsList(keyword,pstype);

        CacheProviders.getCache()
                .getPsList(mapInfoList,new DynamicKey("psList"+keyword),new EvictDynamicKey(false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<PsListInfo>() {
                    @Override
                    public void onSuccess(PsListInfo psListInfo) {
                        if(psListInfo!=null&&psListInfo.getData()!=null&&psListInfo.getData().size()!=0){
                            psListView.showList(psListInfo);
                        }else {
                            psListView.showEmpty();
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
    }

    public void refresh(String keyword,String typename){
        io.reactivex.Observable<PsListInfo> mapInfoList = new ServiceManager().getPsList(keyword,typename);

        CacheProviders.getCache()
                .getPsList(mapInfoList,new DynamicKey("map"),new EvictDynamicKey(true))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<PsListInfo>() {
                    @Override
                    public void onSuccess(PsListInfo psListInfo) {
                        if(psListInfo!=null&&psListInfo.getData()!=null&&psListInfo.getData().size()!=0){
                            psListView.updateListView(psListInfo);
                        }else {
                            psListView.showEmpty();
                        }
                        psListView.endRefresh();
                    }

                    @Override
                    public void onFail(Throwable e) {
                        psListView.showError();
                        psListView.endRefresh();
                    }
                });
    }
}

