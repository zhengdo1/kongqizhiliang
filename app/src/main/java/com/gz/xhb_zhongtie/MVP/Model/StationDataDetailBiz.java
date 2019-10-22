package com.gz.xhb_zhongtie.MVP.Model;

import com.gz.xhb_zhongtie.MVP.Interfaces.IStationDataDetailBiz;
import com.gz.xhb_zhongtie.MVP.Model.Entity.BaseArrayVO;
import com.gz.xhb_zhongtie.MVP.Model.Entity.MapStationDataDetail;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @author zdj
 * @date 2019/10/22.
 * description：
 */
public class StationDataDetailBiz implements IStationDataDetailBiz {
    @Override
    public Observable<BaseArrayVO<MapStationDataDetail>> getStationDataDetail(String pscode, String outputcode, String istoutput) {
        return new ServiceManager().getMapStationDataDetail(pscode, outputcode, istoutput);
    }
}
