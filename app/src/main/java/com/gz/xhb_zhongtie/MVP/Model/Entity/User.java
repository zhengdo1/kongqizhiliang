package com.gz.xhb_zhongtie.MVP.Model.Entity;

import java.io.Serializable;

/**
 * Created by xjj on 2018/5/21.
 *
 */

public class User implements Serializable{
    String returnCode;
    String returnMessage;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}
