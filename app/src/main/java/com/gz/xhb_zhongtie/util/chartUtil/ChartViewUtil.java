package com.gz.xhb_zhongtie.util.chartUtil;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.gz.xhb_zhongtie.CustomView.XYMarkerView;
import com.gz.xhb_zhongtie.R;
import com.gz.xhb_zhongtie.util.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zdj on 2018/7/4.
 */
public class ChartViewUtil {
    public static void showLineCharts(Context context, LinearLayout linearLayout, List<List<Entry>> entriesList, List<String> titles, List<String> dates) {
        linearLayout.removeAllViews();
//        for (List<Entry> list : entriesList) {
        for (int i=0;i<entriesList.size();i++) {
            List<Entry> list = entriesList.get(i);
            LinearLayout itemLinearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_chart_view,null);
            // programmatically create a LineChart
            LineChart chart = itemLinearLayout.findViewById(R.id.lineChart_itemChart_chart);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtil.dp2px(context, 200));
//            int margin = UIUtil.dp2px(context, 84);
//            params.setMargins(0, margin, 0, margin);
//            chart.setLayoutParams(params);
            setLineChartStyle(context, chart, dates,dates);

            List<List<Entry>> list1 = new ArrayList<>();
            list1.add(list);
            setLinesChartData(context,chart, list1, titles.get(i), true, LINE_COLORS, 0);
//            LineDataSet dataSet = new LineDataSet(list, "Label"); // add entries to dataset
////            dataSet.setColor(...);
////            dataSet.setValueTextColor(...); // styling, ...
//            LineData lineData = new LineData(dataSet);
//            chart.setData(lineData);
//            chart.invalidate(); // refresh
            // get a layout defined in xml
            linearLayout.addView(itemLinearLayout); // add the programmatically created chart
        }
    }

    public static void setLineChartStyle(Context context, LineChart lineChart, List<String> xAxisValue,List<String> dates) {
        lineChart.notifyDataSetChanged();
        lineChart.getDescription().setEnabled(false);//设置描述
        lineChart.setPinchZoom(true);//设置按比例放缩柱状图
        lineChart.fitScreen();
//        lineChart.setViewPortOffsets(60,15,15,30);

        //x坐标轴设置
        IAxisValueFormatter xAxisFormatter = new StringAxisValueFormatter(xAxisValue);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        /*xAxis.setAxisLineWidth(2f);*/
        xAxis.setValueFormatter(xAxisFormatter);
        if (xAxisValue.size() > 10) {
//            xAxis.setLabelCount(xAxisValue.size() / 2, false);
            xAxis.setLabelCount(5, false);
        }

        //y轴设置
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setDrawGridLines(false);
//        if (showSetValues) {
//            leftAxis.setDrawLabels(true);//折线上显示值，则不显示坐标轴上的值
//        }
//        leftAxis.setSpaceTop(10f);
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                DecimalFormat fnum = new DecimalFormat("###0.0");
                String v = fnum.format(value);
                return v;//这里可以随意定义你要显示的数据，value是y轴上的值
            }
        });
        leftAxis.setAxisMinimum(0.0f);
        leftAxis.setSpaceTop(20f);
        lineChart.getAxisRight().setEnabled(false);


        XYMarkerView mv = new XYMarkerView(context, xAxisFormatter,dates);
        lineChart.setMarker(mv);
        //图例设置
        Legend legend = lineChart.getLegend();
        legend.setXEntrySpace(1f);
//        legend.setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(true);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        legend.setForm(Legend.LegendForm.LINE);
        legend.setFormToTextSpace(10f);
        legend.setTextSize(10f);

//        List<ILineDataSet> sets = lineChart.getData()
//                .getDataSets();
//
//        for (ILineDataSet iSet : sets) {
//
//            LineDataSet set = (LineDataSet) iSet;
//            if (set.isDrawCirclesEnabled())
//                set.setDrawCircles(false);
//            else
//                set.setDrawCircles(true);
//        }
//        lineChart.invalidate();
    }


    private static void setLinesChartData(Context context,LineChart lineChart, List<List<Entry>> yXAxisValues
            , String title
            , boolean showSetValues, int[] lineColors, int colorIndex) {

        List<List<Entry>> entriesList = yXAxisValues;
//        List<List<Entry>> entriesList = new ArrayList<>();
//        for (int i = 0; i < yXAxisValues.size(); ++i) {
//            ArrayList<Entry> entries = new ArrayList<>();
//            for (int j = 0, n = yXAxisValues.get(i).size(); j < n; j++) {
//                entries.add(new Entry(j, yXAxisValues.get(i).get(j)));
//            }
//            entriesList.add(entries);
//        }

        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {

            for (int i = 0; i < lineChart.getData().getDataSetCount(); ++i) {
                LineDataSet set = (LineDataSet) lineChart.getData().getDataSetByIndex(i);
                set.setValues(entriesList.get(i));
                set.setLabel(title);
                set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            }

            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();

            for (int i = 0; i < entriesList.size(); ++i) {
//                LineDataSet set = new LineDataSet(entriesList.get(i), titles.get(i));
                LineDataSet set = new LineDataSet(entriesList.get(i), title);

                set.setValueFormatter(new IValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                        return "" + value;
                    }
                });
                set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                if (lineColors != null) {
                    set.setColor(lineColors[i % entriesList.size()]);
                } else {
                }
                setSet(context,set,colorIndex);
                dataSets.add(set);
            }

            LineData data = new LineData(dataSets);
            if (showSetValues) {
                data.setValueTextSize(10f);
                data.setValueFormatter(new IValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, Entry entry, int i, ViewPortHandler viewPortHandler) {
                        return StringUtils.double2String(value, 2);
                    }
                });
            } else {
                data.setDrawValues(false);
            }

            lineChart.setData(data);
        }
    }

    private static void setSet(Context context,LineDataSet set,int i) {
        set.setColor(LINE_COLORS[i % 3+i]);
        set.setCircleColor(LINE_COLORS[i % 3+i]);
        set.setCircleColorHole(Color.WHITE);
        set.setDrawCircles(false);
        set.setDrawFilled(true);
        set.setFillColor(LINE_FILL_COLORS[i % 3+i]);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(false);
        if (Utils.getSDKInt() >= 18) {
            // fill drawable only supported on api level 18 and above
            Drawable drawable = ContextCompat.getDrawable(context, R.drawable.fade_red);
            set.setFillDrawable(drawable);
        }
    }


//    /**
//     * 绘制线图，默认最多绘制三种颜色。所有线均依赖左侧y轴显示。
//     *
//     * @param lineChart
//     * @param xAxisValue x轴的轴
//     * @param yXAxisValues y轴的值
//     * @param titles 每一个数据系列的标题
//     * @param showSetValues 是否在折线上显示数据集的值。true为显示，此时y轴上的数值不可见，否则相反。
//     * @param lineColors 线的颜色数组。为null时取默认颜色，此时最多绘制三种颜色。
//     */
//    public static void setLinesChart(Context context,final LineChart lineChart,final List<String> xAxisValue,final List<List<Float>> yXAxisValues,final List<String> titles, boolean showSetValues, int[] lineColors,int colorIndex,final String standard) {
//        lineChart.notifyDataSetChanged();
//        lineChart.getDescription().setEnabled(false);//设置描述
//        lineChart.setPinchZoom(true);//设置按比例放缩柱状图
//        lineChart.fitScreen();
////        lineChart.setViewPortOffsets(60,15,15,30);
//
//        //x坐标轴设置
//        IAxisValueFormatter xAxisFormatter = new StringAxisValueFormatter(xAxisValue);
//        XAxis xAxis = lineChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(false);
//        xAxis.setGranularity(1f);
//        /*xAxis.setAxisLineWidth(2f);*/
//        xAxis.setValueFormatter(xAxisFormatter);
//        if (xSize>10) {
//            xAxis.setLabelCount(xSize/2, false);
//        }
//
//        //y轴设置
//        YAxis leftAxis = lineChart.getAxisLeft();
//        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//        leftAxis.setDrawGridLines(false);
//        if (showSetValues) {
//            leftAxis.setDrawLabels(true);//折线上显示值，则不显示坐标轴上的值
//        }
////        leftAxis.setSpaceTop(10f);
//        leftAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                DecimalFormat fnum = new DecimalFormat("###0.0");
//                String v=fnum.format(value);
//                return v;//这里可以随意定义你要显示的数据，value是y轴上的值
//            }
//        });
//        lineChart.getAxisRight().setEnabled(false);
//
//
//
//        XYMarkerView mv = new XYMarkerView(context, xAxisFormatter);
//        lineChart.setMarker(mv);
//
//        //图例设置
//        Legend legend = lineChart.getLegend();
//        legend.setXEntrySpace(1f);
//        legend.setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);
////        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
////        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        legend.setDrawInside(true);
//        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
//        legend.setForm(Legend.LegendForm.LINE);
//        legend.setTextSize(10f);
//
//
//        //设置折线图数据
//        setLinesChartData(lineChart, yXAxisValues, titles, showSetValues,lineColors,colorIndex);
//
////        lineChart.setExtraOffsets(10, 30, 20, 10);
//        lineChart.animateX(1500);//数据显示动画，从左往右依次显示
//
//        leftAxis.removeAllLimitLines();
//        boolean allZero=true;
//        for (int i=0;i<yXAxisValues.size();i++){
//            allZero=allZero&&hasSame(yXAxisValues.get(i));
//        }
//        leftAxis.setSpaceTop(40f);
//        leftAxis.setStartAtZero(false);
//        if (standard!=null&&!standard.equals("")) {
//
//            // 获得左侧侧坐标轴
//
//            if (yMax>0.7*Float.valueOf(standard)){
//                LimitLine yLimitLine = new LimitLine(Float.valueOf(standard), "标准值");
//                yLimitLine.setLineColor(context.getResources().getColor(R.color.chartLimitLine));
//                yLimitLine.setTextColor(context.getResources().getColor(R.color.chartLimitLine));
//                yLimitLine.setLineWidth(0.5f);
//                leftAxis.addLimitLine(yLimitLine);
////                leftAxis.setAxisMinValue(yMin);
////                leftAxis.setAxisMaximum(yMax+20);
//            }
////            else if (yMax<Float.valueOf(standard)){
//////                leftAxis.setAxisMaxValue(Float.valueOf(standard)*6/5);
//////                leftAxis.setAxisMinValue(yMin);
////
////            }
////            else if (allZero){
//////                leftAxis.setAxisMaximum(10);
////            }
//        }
////        else{
////            if (allZero){
//////                leftAxis.setAxisMinValue(yMin);
//////                leftAxis.setAxisMaximum(10);
////            }else{
//////                leftAxis.setAxisMinValue(yMin);
//////                leftAxis.setAxisMaximum(yMax);
////            }
////        }
//
//    }

    public static final int[] LINE_COLORS = {
            Color.rgb(64, 128, 244), Color.rgb(24, 189, 164), Color.rgb(255, 129, 4)
    };//绿色，紫色，黄色
    public static final int[] LINE_FILL_COLORS = {
            Color.rgb(161, 198, 255), Color.rgb(159, 231, 220), Color.rgb(255, 215, 175)
    };


}
