package com.gz.xhb_zhongtie.util;


import io.reactivex.observers.DefaultObserver;

/**
 * Created by z on 2018/6/27.
 */

public abstract class MyObserver<T> extends DefaultObserver<T> {
    @Override
    protected void onStart() {
        super.onStart();
//        DialogUtils.getInstances().showDialog();
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFail(e);
    }

    @Override
    public void onComplete() {
//        DialogUtils.getInstances().cancel();
    }

    // 获取成功的回调
    public abstract void onSuccess(T t);

    // 获取失败回调
    public abstract void onFail(Throwable e);

}