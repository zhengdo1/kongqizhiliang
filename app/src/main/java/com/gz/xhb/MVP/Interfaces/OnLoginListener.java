package com.gz.xhb.MVP.Interfaces;

import com.gz.xhb.MVP.Model.Entity.User;

/**
 * Created by xjj on 2018/5/21.
 */

public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFailed();
}
