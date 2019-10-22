package com.gz.xhb_zhongtie.MVP.View;

import android.widget.TextView;

import com.gz.xhb_zhongtie.MVP.Model.Entity.DataWaterInfo;

import org.json.JSONArray;

/**
 * Created by zdj on 2018/6/30.
 */
public interface OnlineDataView  extends com.gz.xhb_zhongtie.MVP.View.BaseView {
    void showWaterData(DataWaterInfo dataWaterInfo);
    void showData(JSONArray jsonArray);
//    void showGasData();
//    void showVOCData();
    void showEmpty();
    void setTimeListener();

}
