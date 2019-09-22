package com.gz.xhb.MVP.Model;

import com.gz.xhb.MVP.Model.Entity.BaseArrayVO;
import com.gz.xhb.MVP.Model.Entity.Map;

import io.reactivex.Observable;

/**
 * Created by xjj on 2018/6/22.
 */
public class MapBiz {
    public Observable<BaseArrayVO<Map>> getMapPsList() {
        return new ServiceManager().getMapPsList();
    }
}
