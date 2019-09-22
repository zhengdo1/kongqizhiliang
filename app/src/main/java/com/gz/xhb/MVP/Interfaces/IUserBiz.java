package com.gz.xhb.MVP.Interfaces;


import com.gz.xhb.MVP.Model.Entity.User;

import io.reactivex.Observable;

/**
 * Created by xjj on 2018/5/21.
 */

public interface IUserBiz {
    Observable<User> login(String username, String password);
    boolean  isRemember();
    void  remember(String username, String password);
    void  unRemember();
//    Observable<User> login(String username, String password);
//    Observable<User> login(String username, String password);
}
