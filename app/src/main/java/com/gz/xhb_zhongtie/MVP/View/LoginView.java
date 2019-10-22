package com.gz.xhb_zhongtie.MVP.View;

import com.gz.xhb_zhongtie.MVP.Model.Entity.User;

/**
 * Created by xjj on 2018/5/21.
 */

public interface LoginView extends com.gz.xhb_zhongtie.MVP.View.BaseView {
    void setUser(String user);
    void setPassword(String password);
    String getUser();
    String getPassword();
    void clearUserInfo();
    void toMainActivity(User user);

    void setChecked();
    void setUnChecked();
    void showRememberedUser();
    boolean rememberIsChecked();

}
