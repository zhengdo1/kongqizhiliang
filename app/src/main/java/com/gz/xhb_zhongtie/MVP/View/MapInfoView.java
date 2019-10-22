package com.gz.xhb_zhongtie.MVP.View;

import com.gz.xhb_zhongtie.MVP.Model.Entity.Map;
import com.gz.xhb_zhongtie.MVP.Model.Entity.User;

import java.util.List;

/**
 * Created by xjj on 2018/5/21.
 */

public interface MapInfoView extends BaseView {
    void showPsDot(List<Map> list);
}
