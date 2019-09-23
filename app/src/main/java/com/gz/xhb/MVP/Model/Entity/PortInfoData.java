package com.gz.xhb.MVP.Model.Entity;

import java.io.Serializable;

/**
 * Created by zdj on 2018/6/27.
 */
public class PortInfoData implements Serializable {
    /**
     * outputcode : 3
     * outputname : 污水排口
     * MN :
     * outputtype : 污水
     * outputpointtype : 出口
     * ifsintering :
     * isonline : 离线
     * airstationtype :
     */

    private String outputcode;
    private String outputname;
    private String MN;
    private String outputtype;
    private String outputpointtype;
    private String ifsintering;
    private String isonline;
    private String airstationtype;
    private String airposition;

    public String getAirposition() {
        return airposition;
    }

    public void setAirposition(String airposition) {
        this.airposition = airposition;
    }

    public String getOutputcode() {
        return outputcode;
    }

    public void setOutputcode(String outputcode) {
        this.outputcode = outputcode;
    }

    public String getOutputname() {
        return outputname;
    }

    public void setOutputname(String outputname) {
        this.outputname = outputname;
    }

    public String getMN() {
        return MN;
    }

    public void setMN(String MN) {
        this.MN = MN;
    }

    public String getOutputtype() {
        return outputtype;
    }

    public void setOutputtype(String outputtype) {
        this.outputtype = outputtype;
    }

    public String getOutputpointtype() {
        return outputpointtype;
    }

    public void setOutputpointtype(String outputpointtype) {
        this.outputpointtype = outputpointtype;
    }

    public String getIfsintering() {
        return ifsintering;
    }

    public void setIfsintering(String ifsintering) {
        this.ifsintering = ifsintering;
    }

    public String getIsonline() {
        return isonline;
    }

    public void setIsonline(String isonline) {
        this.isonline = isonline;
    }

    public String getAirstationtype() {
        return airstationtype;
    }

    public void setAirstationtype(String airstationtype) {
        this.airstationtype = airstationtype;
    }

}
