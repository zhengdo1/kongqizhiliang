package com.gz.xhb.MVP.Model;

import com.gz.xhb.MVP.Model.Entity.BaseArrayVO;
import com.gz.xhb.MVP.Model.Entity.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by xjj on 2018/6/22.
 */
public class WaterDataBiz {
    public Observable<ResponseBody> getOnlineData(String pscode, String outputcode, String datatype, String outputtype, String begintime, String endtime, String pollutantcode) {
        return new ServiceManager().getOnlineData(pscode, outputcode, datatype, outputtype, begintime, endtime, pollutantcode);
    }
}
