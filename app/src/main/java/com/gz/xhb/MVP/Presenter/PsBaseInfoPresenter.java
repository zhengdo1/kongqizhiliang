package com.gz.xhb.MVP.Presenter;

import com.gz.xhb.MVP.Model.API.cache.CacheProviders;
import com.gz.xhb.MVP.Model.Entity.BaseArrayVO;
import com.gz.xhb.MVP.Model.Entity.Map;
import com.gz.xhb.MVP.Model.Entity.PsBaseInfo;
import com.gz.xhb.MVP.Interfaces.OnGetPsBaseInfoListener;
import com.gz.xhb.MVP.Model.PsBaseInfoBiz;
import com.gz.xhb.MVP.Model.ServiceManager;
import com.gz.xhb.MVP.View.PsBaseInfoView;
import com.gz.xhb.util.MyObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;

/**
 * Created by xjj on 2018/5/21.
 */

public class PsBaseInfoPresenter {
    PsBaseInfoView psBaseInfoView;
    PsBaseInfoBiz biz;

    public PsBaseInfoPresenter(PsBaseInfoView psBaseInfoView) {
        this.psBaseInfoView = psBaseInfoView;
        biz = new PsBaseInfoBiz();
    }

    public void getPsBaseInfo(String pscode) {
        io.reactivex.Observable<PsBaseInfo> mapInfoList = new ServiceManager().getPsBaseInfo(pscode);

        CacheProviders.getCache()
                .getPsBaseInfo(mapInfoList, new DynamicKey("psBaseInfo" + pscode), new EvictDynamicKey(false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<PsBaseInfo>() {
                    @Override
                    public void onSuccess(PsBaseInfo psBaseInfo) {
                        psBaseInfoView.showPsBaseInfo(psBaseInfo.getData().get(0));
                    }

                    @Override
                    public void onFail(Throwable e) {
                        psBaseInfoView.showError();
                    }
                });
    }


//    @Module
//    public class MainModule {
//        private final LoginView mView;
//
//        public MainModule(LoginView view) {
//            mView = view;
//        }
//
//        @Provides
//        LoginView provideMainView() {
//            return mView;
//        }
//    }


//    @Component()
//    public interface LoginComponent {
//        void inject(LoginActivity activity);
//    }
}
