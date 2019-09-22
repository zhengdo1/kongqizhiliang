package com.gz.xhb.MVP.View;

import com.gz.xhb.MVP.Model.Entity.Map;
import com.gz.xhb.MVP.Model.Entity.User;

import java.util.List;

/**
 * Created by xjj on 2018/5/21.
 */

public interface MapInfoView extends BaseView {
    void showPsDot(List<Map> list);
}
