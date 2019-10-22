package com.gz.xhb_zhongtie.MVP.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.gz.xhb_zhongtie.MVP.Interfaces.IUserBiz;
import com.gz.xhb_zhongtie.MVP.Interfaces.OnLoginListener;
import com.gz.xhb_zhongtie.MVP.Model.Entity.User;
import com.gz.xhb_zhongtie.MyApplication.MyApplication;
import com.gz.xhb_zhongtie.util.SharedPreferencesUtils;

import io.reactivex.Observable;

/**
 * Created by xjj on 2018/5/21.
 */

public class UserBiz implements IUserBiz {
    @Override
    public Observable<User> login(final String username, final String password) {
        return new ServiceManager().login(username, password);
    }

    @Override
    public boolean isRemember() {
        //判断记住密码多选框的状态
        if ("1".equals(SharedPreferencesUtils.getParam(MyApplication.getContext(),"isRemember","0"))){
            //设置默认是记录密码状态
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void remember(String username, String password) {
        SharedPreferencesUtils.setParam(MyApplication.getContext(),"isRemember","1");
        SharedPreferencesUtils.setParam(MyApplication.getContext(),"name",username);
        SharedPreferencesUtils.setParam(MyApplication.getContext(),"password",password);
    }

    @Override
    public void unRemember() {
        SharedPreferencesUtils.setParam(MyApplication.getContext(),"isRemember","0");
        SharedPreferencesUtils.setParam(MyApplication.getContext(),"name","");
        SharedPreferencesUtils.setParam(MyApplication.getContext(),"password","");
    }


}

//        io.reactivex.Observable<ResponseBody> login = new ServiceManager().login("18032033357","123456","outerApp");
//
//        login.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ResponseBody>() {
//
//                    @Override
//                    public void onSubscribe(Disposable disposable) {
//
//                    }
//
//                    @Override
//                    public void onNext(ResponseBody responseBody) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

//        //模拟子线程耗时操作
//        new Thread()
//        {
//            @Override
//            public void run()
//            {
//                try
//                {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e)
//                {
//                    e.printStackTrace();
//                }
//                //模拟登录成功
////                if ("zhy".equals(username) && "123".equals(password))
//                if (true)
//                {
//                    User user = new User();
//                    user.setUsername(username);
//                    user.setPassword(password);
//                    loginListener.loginSuccess(user);
//                } else
//                {
//                    loginListener.loginFailed();
//                }
//            }
//        }.start();
//    }
//}
