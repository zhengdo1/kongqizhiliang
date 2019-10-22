package com.gz.xhb.util;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.gz.xhb.R;

import java.text.DecimalFormat;

/**
 * Created by dell on 2017/11/16.
 */
public class XYNewMarkerView extends MarkerView {

    private TextView tvContent;

    private DecimalFormat format;

    public XYNewMarkerView(Context context) {
        super(context, R.layout.item_mark_layout);
        tvContent = (TextView) findViewById(R.id.tv_markerView_content);
        format = new DecimalFormat("####0.00");
    }

    //回调函数每次MarkerView重绘,可以用来更新内容(用户界面)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText(format.format(e.getY()));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
