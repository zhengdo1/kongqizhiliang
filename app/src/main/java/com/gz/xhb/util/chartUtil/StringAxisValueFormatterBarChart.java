package com.gz.xhb.util.chartUtil;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/**
 * Created by Charlie on 2016/9/23.
 * 对字符串类型的坐标轴标记进行格式化
 */
public class StringAxisValueFormatterBarChart implements IAxisValueFormatter {

    //区域值
    private List<String> mStrs;

    /**
     * 对字符串类型的坐标轴标记进行格式化
     *
     * @param strs
     */
    public StringAxisValueFormatterBarChart(List<String> strs) {
        this.mStrs = strs;
    }


    @Override
    public String getFormattedValue(float v, AxisBase axisBase) {
        if (v < 0) {
            return "";
        } else {
            String str = mStrs.get((int) v);
            str = str.replace("\\n", " ");
            str = str.replace("\\r", "");
            return str;
        }
    }
}
