package com.gz.xhb.MVP.View;

import com.gz.xhb.MVP.Model.Entity.User;

/**
 * Created by xjj on 2018/5/21.
 */

public interface LoginView extends com.gz.xhb.MVP.View.BaseView {
    String getUser();
    String getPassword();
    void clearUserInfo();
    void toMainActivity(User user);

    void setChecked();
    void setUnChecked();
    void showRememberedUser();
    boolean rememberIsChecked();

}
