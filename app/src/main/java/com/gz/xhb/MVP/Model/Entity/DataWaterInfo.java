package com.gz.xhb.MVP.Model.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xjj on 2018/6/13.
 */

public class DataWaterInfo implements Serializable {


    /**
     * total : 2
     * rows : [{"监测时间":"2018-06-27 01:00","COD平均浓度(mg/L)":"","氨氮平均浓度(mg/L)":"","污水排放量(t)":""},{"监测时间":"2018-06-27 00:00","COD平均浓度(mg/L)":"","氨氮平均浓度(mg/L)":"","污水排放量(t)":""}]
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * 监测时间 : 2018-06-27 01:00
         * COD平均浓度(mg/L) :
         * 氨氮平均浓度(mg/L) :
         * 污水排放量(t) :
         */

        private String 监测时间;
        @SerializedName("COD平均浓度(mg/L)")
        private String _$CODMgL51; // FIXME check this code
        @SerializedName("氨氮平均浓度(mg/L)")
        private String _$MgL256; // FIXME check this code
        @SerializedName("污水排放量(t)")
        private String _$T267; // FIXME check this code

        public String get监测时间() {
            return 监测时间;
        }

        public void set监测时间(String 监测时间) {
            this.监测时间 = 监测时间;
        }

        public String get_$CODMgL51() {
            return _$CODMgL51;
        }

        public void set_$CODMgL51(String _$CODMgL51) {
            this._$CODMgL51 = _$CODMgL51;
        }

        public String get_$MgL256() {
            return _$MgL256;
        }

        public void set_$MgL256(String _$MgL256) {
            this._$MgL256 = _$MgL256;
        }

        public String get_$T267() {
            return _$T267;
        }

        public void set_$T267(String _$T267) {
            this._$T267 = _$T267;
        }
    }
}
