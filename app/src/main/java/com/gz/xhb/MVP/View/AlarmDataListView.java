package com.gz.xhb.MVP.View;

import com.gz.xhb.MVP.Model.Entity.PortInfoData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by zdj on 2018/6/24.
 */
public interface AlarmDataListView extends BaseView {
    void showList(List<JSONObject> list);
    void showError();
    void updateListView(List<JSONObject> list);
    void endRefresh();
    void endLoadMore();
    void showEmpty();

}
