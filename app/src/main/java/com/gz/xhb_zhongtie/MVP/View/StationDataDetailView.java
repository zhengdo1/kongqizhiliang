package com.gz.xhb_zhongtie.MVP.View;

import com.gz.xhb_zhongtie.MVP.Model.Entity.MapStationDataDetail;
import com.gz.xhb_zhongtie.MVP.Model.Entity.User;

import java.util.List;

/**
 * Created by xjj on 2018/5/21.
 */

public interface StationDataDetailView extends BaseView {
    void showData1();
    void showData2(List<MapStationDataDetail> mapStationDataDetails);
}
