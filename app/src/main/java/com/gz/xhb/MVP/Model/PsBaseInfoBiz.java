package com.gz.xhb.MVP.Model;

import com.gz.xhb.MVP.Model.Entity.BaseArrayVO;
import com.gz.xhb.MVP.Model.Entity.Map;
import com.gz.xhb.MVP.Model.Entity.PsBaseInfo;
import com.gz.xhb.MVP.Interfaces.IPsBaseInfoBiz;
import com.gz.xhb.MVP.Interfaces.OnGetPsBaseInfoListener;

import io.reactivex.Observable;

/**
 * Created by xjj on 2018/6/6.
 */

public class PsBaseInfoBiz{
    public Observable<PsBaseInfo> getMapPsList(String pscode) {
        return new ServiceManager().getPsBaseInfo(pscode);
    }
}
