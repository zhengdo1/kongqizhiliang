package com.gz.xhb.CustomView;

import android.content.Context;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.gz.xhb.R;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by www on 2018/07/17.
 */
public class XYMarkerView extends MarkerView {

    private TextView tvContent;
    private IAxisValueFormatter xAxisValueFormatter;
    private List<String> xValues;

    private DecimalFormat format;

    public XYMarkerView(Context context, IAxisValueFormatter xAxisValueFormatter, List<String> xValues) {
        super(context, R.layout.item_mark_layout);

        this.xAxisValueFormatter = xAxisValueFormatter;
        this.xValues = xValues;
        tvContent = (TextView) findViewById(R.id.tv_markerView_content);
        format = new DecimalFormat("####0.00");
    }

    //回调函数每次MarkerView重绘,可以用来更新内容(用户界面)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        String content = "";
        try {
            content = "时间:" + xValues.get((int) e.getX());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        content = content + "        数值:" + format.format(e.getY());
        tvContent.setText(content);
        super.refreshContent(e, highlight);
    }

//    @Override
//    public MPPointF getOffset() {
////        ViewParent view =  get;/
//        return new MPPointF(-(getWidth() / 2), -getHeight());
//    }

    @Override
    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
        MPPointF mOffset2 = new MPPointF();

        MPPointF offset = getOffset();
        mOffset2.x = offset.x;
        mOffset2.y = offset.y;

        Chart chart = getChartView();

        float width = getWidth();
        float height = getHeight();

        if (posX + mOffset2.x < 0) {
            mOffset2.x = -posX;
        } else if (chart != null && posX + width + mOffset2.x > chart.getWidth()) {
//            mOffset2.x = chart.getWidth() - posX - width;
            mOffset2.x = 0;
        }

        if (posY + mOffset2.y < 0) {
            mOffset2.y = -posY;
        } else if (chart != null && posY + height + mOffset2.y > chart.getHeight()) {
            mOffset2.y = chart.getHeight() - posY - height;
            mOffset2.y = 0;
        }

        return new MPPointF(0-posX,0-posY);
    }
}
