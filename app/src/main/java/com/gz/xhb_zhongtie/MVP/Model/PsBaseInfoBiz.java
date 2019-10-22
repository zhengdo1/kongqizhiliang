package com.gz.xhb_zhongtie.MVP.Model;

import com.gz.xhb_zhongtie.MVP.Model.Entity.BaseArrayVO;
import com.gz.xhb_zhongtie.MVP.Model.Entity.Map;
import com.gz.xhb_zhongtie.MVP.Model.Entity.PsBaseInfo;
import com.gz.xhb_zhongtie.MVP.Interfaces.IPsBaseInfoBiz;
import com.gz.xhb_zhongtie.MVP.Interfaces.OnGetPsBaseInfoListener;

import io.reactivex.Observable;

/**
 * Created by xjj on 2018/6/6.
 */

public class PsBaseInfoBiz{
    public Observable<PsBaseInfo> getMapPsList(String pscode) {
        return new ServiceManager().getPsBaseInfo(pscode);
    }
}
