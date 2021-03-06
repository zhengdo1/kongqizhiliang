package com.gz.xhb_zhongtie.MVP.Model.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xjj on 2018/6/22.
 */
public class MapStationDataDetail implements Parcelable {


    /**
     * monitortime : 20日\n\r18时
     * avgvalue : null
     * datacolor : null
     */

    private String monitortime;
    private String avgvalue;
    private String datacolor;

    protected MapStationDataDetail(Parcel in) {
        monitortime = in.readString();
    }

    public static final Creator<MapStationDataDetail> CREATOR = new Creator<MapStationDataDetail>() {
        @Override
        public MapStationDataDetail createFromParcel(Parcel in) {
            return new MapStationDataDetail(in);
        }

        @Override
        public MapStationDataDetail[] newArray(int size) {
            return new MapStationDataDetail[size];
        }
    };

    public String getMonitortime() {
        return monitortime;
    }

    public void setMonitortime(String monitortime) {
        this.monitortime = monitortime;
    }

    public String getAvgvalue() {
        return avgvalue;
    }

    public void setAvgvalue(String avgvalue) {
        this.avgvalue = avgvalue;
    }

    public String getDatacolor() {
        return datacolor;
    }

    public void setDatacolor(String datacolor) {
        this.datacolor = datacolor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(monitortime);
    }
}
