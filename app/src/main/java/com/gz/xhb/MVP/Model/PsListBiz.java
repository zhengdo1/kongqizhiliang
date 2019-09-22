package com.gz.xhb.MVP.Model;

import com.gz.xhb.MVP.Model.Entity.BaseArrayVO;
import com.gz.xhb.MVP.Model.Entity.PsBaseInfo;
import com.gz.xhb.MVP.Model.Entity.PsListInfo;

import io.reactivex.Observable;

/**
 * Created by xjj on 2018/6/22.
 */
public class PsListBiz {
    public Observable<PsListInfo> getPsList(String keyword,String pstype) {
        return new ServiceManager().getPsList(keyword,pstype);
    }
}
