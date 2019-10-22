package com.gz.xhb_zhongtie.util.chartUtil;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.gz.xhb_zhongtie.R;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by dell on 2017/11/16.
 */
public class XYNewMarkerView extends MarkerView {

    private TextView tvContent;

    private DecimalFormat format;
    List<String> times;

    public XYNewMarkerView(Context context, List<String> times) {
        super(context, R.layout.item_mark_layout);
        tvContent = (TextView) findViewById(R.id.tv_markerView_content);
        format = new DecimalFormat("####0.00");
        this.times = times;
    }

    //回调函数每次MarkerView重绘,可以用来更新内容(用户界面)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        String time = times.get((int) e.getX());
        time = time.replace("\\n", "");
        time = time.replace("\\r", "");
        tvContent.setText(time + "  " + format.format(e.getY()));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
