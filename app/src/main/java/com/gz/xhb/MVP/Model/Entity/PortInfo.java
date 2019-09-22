package com.gz.xhb.MVP.Model.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xjj on 2018/6/6.
 */

public class PortInfo implements Serializable {

    /**
     * ok : true
     * data : [{"outputcode":"3","outputname":"污水排口","MN":"","outputtype":"污水","outputpointtype":"出口","ifsintering":"","isonline":"离线","airstationtype":""},{"outputcode":"2","outputname":"VOC监控点1","MN":"SZ131182000002","outputtype":"废气","outputpointtype":"出口","ifsintering":"否","isonline":"离线","airstationtype":""},{"outputcode":"1","outputname":"废气排口1","MN":"SZ131182000001","outputtype":"废气","outputpointtype":"出口","ifsintering":"否","isonline":"离线","airstationtype":""}]
     */

    private boolean ok;
    private List<PortInfoData> data;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<PortInfoData> getData() {
        return data;
    }

    public void setData(List<PortInfoData> data) {
        this.data = data;
    }

    
}