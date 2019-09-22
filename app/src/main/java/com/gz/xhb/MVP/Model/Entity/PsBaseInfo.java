package com.gz.xhb.MVP.Model.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xjj on 2018/6/6.
 */

public class PsBaseInfo implements Serializable {

    /**
     * ok : true
     * data : [{"pscode":"131182000001","psname":"深州市ABC","attentiondegreecode":"其他","pstypecode":"1","pstypename":"一般工业企业","psaddress":"A乡B村","regioncode":"131182000","regionname":"深州市","orgnizationcode":"","corporate":"","longitude":"114.00","latitude":"41.00","linkman":"","mobile":"","remarks":"","spell":"SZSABC"}]
     */

    private boolean ok;
    private List<PsBaseInfoData> data;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<PsBaseInfoData> getData() {
        return data;
    }

    public void setData(List<PsBaseInfoData> data) {
        this.data = data;
    }


}
