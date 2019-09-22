package com.gz.xhb.MVP.View;

import com.gz.xhb.MVP.Model.Entity.PortInfo;
import com.gz.xhb.MVP.Model.Entity.PortInfoData;
import com.gz.xhb.MVP.Model.Entity.PsListInfo;

import java.util.List;

/**
 * Created by zdj on 2018/6/24.
 */
public interface PortInfoListView extends BaseView {
    void showList(List<PortInfoData> list);
    void showError();
    void updateListView(List<PortInfoData> list);
    void endRefresh();
    void endLoadMore();
    void showEmpty();

}
