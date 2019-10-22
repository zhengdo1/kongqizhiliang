package com.gz.xhb_zhongtie.MVP.Model;

import com.gz.xhb_zhongtie.MVP.Model.Entity.PortInfo;
import com.gz.xhb_zhongtie.MVP.Model.Entity.PsBaseInfo;

import io.reactivex.Observable;

/**
 * Created by xjj on 2018/6/6.
 */

public class PortInfoBiz {
    public Observable<PortInfo> getMapPsList(String pscode,String outputtype) {
        return new ServiceManager().getPsPortList(pscode,outputtype);
    }
}
