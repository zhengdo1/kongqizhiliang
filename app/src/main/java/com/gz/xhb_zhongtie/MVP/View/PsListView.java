package com.gz.xhb_zhongtie.MVP.View;

import com.gz.xhb_zhongtie.MVP.Model.Entity.PsListInfo;

import java.util.List;

/**
 * Created by zdj on 2018/6/24.
 */
public interface PsListView extends BaseView {
    void showList(PsListInfo psListInfo);
    void showError();
    void updateListView(PsListInfo psListInfo);
    void endRefresh();
    void endLoadMore();
    void showEmpty();

}
