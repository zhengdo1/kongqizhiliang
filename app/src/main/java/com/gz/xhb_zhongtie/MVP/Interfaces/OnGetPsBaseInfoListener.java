package com.gz.xhb_zhongtie.MVP.Interfaces;

import com.gz.xhb_zhongtie.MVP.Model.Entity.PsBaseInfo;

/**
 * Created by xjj on 2018/5/21.
 */

public interface OnGetPsBaseInfoListener {
    void onGetSuccess(PsBaseInfo psBaseInfo);
    void onGetFailed();
}
