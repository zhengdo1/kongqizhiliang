package com.gz.xhb.util;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/**
 * Created by Charlie on 2016/9/23.
 * 对字符串类型的坐标轴标记进行格式化
 */
public class StringAxisValueFormatter implements IAxisValueFormatter {

    //区域值
    private List<String> mStrs;

    /**
     * 对字符串类型的坐标轴标记进行格式化
     *
     * @param strs
     */
    public StringAxisValueFormatter(List<String> strs) {
        this.mStrs = strs;
    }

//    @Override
//    public String getFormattedValue(float v, AxisBase axisBase) {
//        if(v<0){
//            return "";
//        }else {
//            if ((int)v<mStrs.size()) {
//                return mStrs.get((int) v);
//            }else{
//                return "";
//            }
//        }
//    }

    @Override
    public String getFormattedValue(float v, AxisBase axisBase) {
        if (v < 0) {
            return "";
        } else {
//            return String.valueOf((int) v);
            String str = mStrs.get((int) v);
            if (str.startsWith("20"))
                str = str.substring(5);

            if (str.startsWith("0"))
                str = str.substring(1);
            if (str.length() > 3)
                str = str.substring(0, str.length() - 3);

            return str;
        }
    }
}
