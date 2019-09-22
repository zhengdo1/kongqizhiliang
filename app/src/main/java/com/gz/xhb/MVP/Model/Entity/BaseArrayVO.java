package com.gz.xhb.MVP.Model.Entity;

import java.util.List;

/**
 * Created by d on 2018/5/15.
 */

public class BaseArrayVO<T> {
    private boolean ok;
    private List<T> data;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

