package com.gz.xhb_zhongtie.MVP.Model.Entity;

/**
 * Created by d on 2018/5/15.
 */

public class BaseVO<T> {
    private boolean ok;

    private T data;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

