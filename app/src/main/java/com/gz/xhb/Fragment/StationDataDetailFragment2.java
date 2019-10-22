package com.gz.xhb.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.gz.xhb.MVP.Model.Entity.MapStationDataDetail;
import com.gz.xhb.R;
import com.gz.xhb.util.Utils;
import com.gz.xhb.util.chartUtil.CustomXAxisRenderer;
import com.gz.xhb.util.chartUtil.StringAxisValueFormatterBarChart;
import com.gz.xhb.util.chartUtil.XYNewMarkerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zdj on 2019/10/21.
 */
public class StationDataDetailFragment2 extends Fragment {


    @BindView(R.id.chart1)
    BarChart chart;
    Unbinder unbinder;
    List<MapStationDataDetail> mapStationDataDetails;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_station_data_detail2, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mapStationDataDetails = bundle.getParcelableArrayList("data");
        } else {
            mapStationDataDetails = new ArrayList<>();
        }
        initChartStyle();
        initChartLabel();

        showChartData(mapStationDataDetails);
        // 设置Y轴进入动画
        chart.animateY(500);
    }

    /**
     * 初始化图表的样式
     */
    protected void initChartStyle() {
        //关闭描述
        chart.getDescription().setEnabled(true);
        Description description = new Description();
         description .setText("PM10小时浓度均值(μg/m³)");
        chart.setDescription(description);
        chart.setBackgroundColor(Color.WHITE);
        //设置显示值时，最大的柱数量
        chart.setMaxVisibleValueCount(60);
        //设置不能同时在x轴和y轴上缩放
        chart.setPinchZoom(false);
        chart.setDrawBarShadow(false);
        //设置不画背景网格
        chart.setDrawGridBackground(false);
        chart.setHighlightFullBarEnabled(false);
        float left= Utils.dip2px(this.getContext(),28);
        float right=Utils.dip2px(this.getContext(),8);
        float bottom=Utils.dip2px(this.getContext(),20);
//        chart.setViewPortOffsets(left, -15f, right, bottom);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setEnabled(true);
        chart.setScaleXEnabled(false);
        chart.setScaleYEnabled(false);

        //设置X轴样式
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        leftAxis.setSpaceTop(15f);
        chart.setNoDataText("没有数据");//没有数据时显示的文字
    }

    /**
     * 初始化图表的 标题 样式
     */
    protected void initChartLabel() {
        //不显示图标 标题
        chart.getLegend().setEnabled(false);
    }

    /**
     * 设置图表数据
     *
     * @param count 柱状图中柱的数量
     * @param range
     */
    protected void setChartData(int count, float range) {
        List<BarEntry> values = new ArrayList<>();
        //设置数据源
        for (int i = 0; i < count; i++) {
            float multi = (range + 1);
            float val = (float) (Math.random() * multi) + multi / 3;
            values.add(new BarEntry(i, val));
        }

        BarDataSet set1;
        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "Data Set");
            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            set1.setDrawValues(false);

            List<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            chart.setData(data);
            chart.setFitBars(true);
        }
        chart.invalidate();
    }

    private void showChartData(List<MapStationDataDetail> list) {
        List<BarEntry> entrieList = new ArrayList<>();
        List<String> times = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        List<String> colors = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            MapStationDataDetail entry = list.get(i);
            String timeStr = entry.getMonitortime();
            String valueStr = entry.getAvgvalue();

            float value = 0.0f;
            if (valueStr == null || valueStr.equals("") || valueStr.equals("-")) {
                value = 0.0f;
            } else {
                try {
                    value = Float.parseFloat(valueStr);
                } catch (Exception e) {
//                            value = 0.0f;
                }
            }
            BarEntry barEntry = new BarEntry(i, value);
//            entries.add(new Entry(i, value));
            entrieList.add(barEntry);
//            for (int i = 0; i < list.size(); i++) {
//                String valueStr = list.get(i).get(entry.getKey());
//                dates.add(valueStr);
//            }
            times.add(timeStr);
            dates.add(valueStr);
            colors.add(entry.getDatacolor());
        }
        int[] colorsInt = new int[colors.size()];
        for (int j = 0; j < colors.size(); j++) {
            String color = colors.get(j);
            if(color!=null&&color.startsWith("#"))
            colorsInt[j] = Color.parseColor(color);
            else {
                colorsInt[j] = Color.parseColor("#00d900");
            }
        }

        BarDataSet set1;
        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(entrieList);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(entrieList, "Data Set");
            set1.setColors(colorsInt);
            set1.setDrawValues(false);

            List<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            chart.setData(data);
            chart.setFitBars(true);
        }
        XYNewMarkerView mv = new XYNewMarkerView(this.getContext());
        mv.setChartView(chart);
        chart.setMarker(mv);

        //x坐标轴设置
        IAxisValueFormatter xAxisFormatter = new StringAxisValueFormatterBarChart(times);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(xAxisFormatter);
        if (times.size() > 12) {
            xAxis.setLabelCount(12, false);
        }
        chart.setExtraBottomOffset(2 * 9f);
        xAxis.setTextSize(9);
        chart.setXAxisRenderer(new CustomXAxisRenderer(chart.getViewPortHandler(), chart.getXAxis(), chart.getTransformer(YAxis.AxisDependency.LEFT)));

        chart.invalidate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
