package com.gz.xhb_zhongtie.MVP.Presenter;

import android.os.Handler;

import com.gz.xhb_zhongtie.MVP.Model.Entity.User;
import com.gz.xhb_zhongtie.MVP.Model.UserBiz;
import com.gz.xhb_zhongtie.MVP.View.LoginView;
import com.gz.xhb_zhongtie.util.MyObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xjj on 2018/5/21.
 */

public class UserLoginPresenter {
    LoginView loginView;
    UserBiz biz;
    private Handler mHandler = new Handler();

    //    @Inject
    public UserLoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        biz = new UserBiz();
    }

    public void login() {
        if(loginView.rememberIsChecked()){
            biz.remember(loginView.getUser(),loginView.getPassword());
        }else {
            biz.unRemember();
        }
        biz.login(loginView.getUser(), loginView.getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<User>() {
                    @Override
                    public void onSuccess(User user) {
                        if(user.getReturnCode()!=null&&user.getReturnCode().equals("1")){
                            loginView.toMainActivity(user);
                        }else {
                            loginView.showError();
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {
                        loginView.showError();
                    }
                });
    }
    public void setCheckRember(LoginView loginView){
        if(biz.isRemember()){
            loginView.setChecked();
            loginView.showRememberedUser();
        }else {
            loginView.setUnChecked();
        }

    }
}








//                , new OnLoginListener() {
//            @Override
//            public void loginSuccess(final User user) {
//                //需要在UI线程执行
//                mHandler.post(new Runnable()
//                {
//                    @Override
//                    public void run()
//                    {
//                        loginView.toMainActivity(user);
//                        loginView.dismissProgressDialog();
//                    }
//                });
//            }
//
//            @Override
//            public void loginFailed() {
//
//                //需要在UI线程执行
//                mHandler.post(new Runnable()
//                {
//                    @Override
//                    public void run()
//                    {
//                        loginView.showError();
//                        loginView.dismissProgressDialog();
//                    }
//                });
//            }
//        });
//    }


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
//}
