package com.gz.xhb.MVP.Model.Entity;

import java.io.Serializable;

/**
 * Created by xjj on 2018/6/22.
 */
public class Map implements Serializable{


    /**
     * id : 15
     * pscode : 130905000002
     * psname : 河北渤海煤焦化有限公司
     * outputcode : 1021
     * outputname : 空气质量微站测点
     * airPosition : 焦化办公楼西南角
     * longitude : 117.827778
     * latitude : 38.304722
     * isToutput : 0
     * levelid : 1
     * isAirStation : 1
     * monitortime : 2019/10/21 21:15:00
     * value_D03 : 121.0
     * value_D07 : 642.0
     * value_D10 : 8.0
     * value_D12 : 49.0
     * value_D13 : 31.0
     * value_D16 : 93.0
     * content : <br/>厂区：河北渤海煤焦化有限公司<br/>点位名称：空气质量微站测点<br/>位置：焦化办公楼西南角<br/>是否为空气站：是<br/>最近监测时间：2019-10-21 21:15:00<br/>二氧化氮(μg/m3)：121.000000<br/>一氧化碳(mg/m3)：642.000000<br/>二氧化硫(μg/m3)：8.000000<br/>PM10(μg/m3)：49.000000<br/>PM2.5(μg/m3)：31.000000<br/>臭氧(μg/m3)：93.000000
     */

    private String id;
    private String pscode;
    private String psname;
    private String outputcode;
    private String outputname;
    private String airPosition;
    private String longitude;
    private String latitude;
    private String isToutput;
    private String levelid;
    private String isAirStation;
    private String monitortime;
    private String value_D03;
    private String value_D07;
    private String value_D10;
    private String value_D12;
    private String value_D13;
    private String value_D16;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPscode() {
        return pscode;
    }

    public void setPscode(String pscode) {
        this.pscode = pscode;
    }

    public String getPsname() {
        return psname;
    }

    public void setPsname(String psname) {
        this.psname = psname;
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

    public String getAirPosition() {
        return airPosition;
    }

    public void setAirPosition(String airPosition) {
        this.airPosition = airPosition;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getIsToutput() {
        return isToutput;
    }

    public void setIsToutput(String isToutput) {
        this.isToutput = isToutput;
    }

    public String getLevelid() {
        return levelid;
    }

    public void setLevelid(String levelid) {
        this.levelid = levelid;
    }

    public String getIsAirStation() {
        return isAirStation;
    }

    public void setIsAirStation(String isAirStation) {
        this.isAirStation = isAirStation;
    }

    public String getMonitortime() {
        return monitortime;
    }

    public void setMonitortime(String monitortime) {
        this.monitortime = monitortime;
    }

    public String getValue_D03() {
        return value_D03;
    }

    public void setValue_D03(String value_D03) {
        this.value_D03 = value_D03;
    }

    public String getValue_D07() {
        return value_D07;
    }

    public void setValue_D07(String value_D07) {
        this.value_D07 = value_D07;
    }

    public String getValue_D10() {
        return value_D10;
    }

    public void setValue_D10(String value_D10) {
        this.value_D10 = value_D10;
    }

    public String getValue_D12() {
        return value_D12;
    }

    public void setValue_D12(String value_D12) {
        this.value_D12 = value_D12;
    }

    public String getValue_D13() {
        return value_D13;
    }

    public void setValue_D13(String value_D13) {
        this.value_D13 = value_D13;
    }

    public String getValue_D16() {
        return value_D16;
    }

    public void setValue_D16(String value_D16) {
        this.value_D16 = value_D16;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
