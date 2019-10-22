package com.gz.xhb.MVP.Interfaces;


import com.gz.xhb.MVP.Model.Entity.BaseArrayVO;
import com.gz.xhb.MVP.Model.Entity.MapStationDataDetail;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by xjj on 2018/5/21.
 */

public interface IStationDataDetailBiz {
    Observable<BaseArrayVO<MapStationDataDetail>> getStationDataDetail(String pscode, String outputcode, String istoutput);
}
