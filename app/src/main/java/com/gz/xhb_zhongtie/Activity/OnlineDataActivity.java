package com.gz.xhb_zhongtie.Activity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;
import com.gz.xhb_zhongtie.Adapter.ListAdapter;
import com.gz.xhb_zhongtie.MVP.Model.Entity.DataWaterInfo;
import com.gz.xhb_zhongtie.MVP.Presenter.OnlineDataPresenter;
import com.gz.xhb_zhongtie.MVP.View.OnlineDataView;
import com.gz.xhb_zhongtie.R;
import com.gz.xhb_zhongtie.util.DateUtils;
import com.gz.xhb_zhongtie.util.JsonUtil;
import com.gz.xhb_zhongtie.util.TimePickerDialogUtil;
import com.gz.xhb_zhongtie.util.ToolBarUtil;
import com.gz.xhb_zhongtie.util.chartUtil.ChartViewUtil;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import org.angmarch.views.NiceSpinner;
import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import vb.shlv.MyHScrollView;

//import com.gz.xhb_zhongtie.util.TimeUtil;

/**
 * Created by xjj on 2018/6/13.
 */

public class OnlineDataActivity extends XHBBaseActivity implements OnlineDataView, OnDateSetListener {
    @BindView(R.id.tv_onlineData_beginTime)
    TextView tvOnlineDataBeginTime;
    @BindView(R.id.tv_onlineData_endTime)
    TextView tvOnlineDataEndTime;
    @BindView(R.id.ll_onlineData_time)
    LinearLayout llOnlineDataTime;
    @BindView(R.id.nice_spinner)
    NiceSpinner niceSpinner;
    @BindView(R.id.ll_onlineData_otherCondition)
    RelativeLayout llOnlineDataOtherCondition;
    @BindView(R.id.ll_onlineData_condition)
    LinearLayout llOnlineDataCondition;
    @BindView(R.id.ll_onlineData_addLayout)
    LinearLayout llOnlineDataAddLayout;
    @BindView(R.id.head_scroll)
    MyHScrollView headScroll;
    @BindView(R.id.ll_onlineData_head)
    LinearLayout llOnlineDataHead;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.btn_onlineData_search)
    Button btnOnlineDataSearch;
    @BindView(R.id.ll_onlineData_charts)
    LinearLayout llOnlineDataCharts;
    @BindView(R.id.sv_onlineData_charts)
    ScrollView svOnlineDataCharts;
    private GestureDetector mGesture;
    // 事件状态
    private final char FLING_NORMAL = 0;
    private final char FLING_CLICK = 1;
    private final char FLING_LEFT = 2;
    private final char FLING_RIGHT = 3;
    private char flingState = FLING_NORMAL;
    String typeName;


    //    private ListView listview;
//    private LinearLayout head;
    private ListAdapter adapter;

    OnlineDataPresenter presenter = new OnlineDataPresenter(this);
    private String outputcode, outputtype, begintime, endtime;
    private String datatype = "2011";
    private String pollutantcode = "0";
    List<String> dataset = new LinkedList<>(Arrays.asList("实时数据", "十分钟数据", "小时数据", "日数据"));
    String[] dataTypes = new String[]{"2011", "2051", "2061", "2031"};


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_online_data;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra("outputname");
        ToolBarUtil.setToolBar(this, title != null ? title : "");
//        tvOnlineDataBeginTime.setText(TimeUtil.getTodayDateStr(new Date()));
//        tvOnlineDataEndTime.setText(TimeUtil.getDateToString(new Date().getTime()));
//        TimeUtil.setBeginToEndTime(this, tvOnlineDataBeginTime, tvOnlineDataEndTime);
        tvOnlineDataBeginTime.setText(DateUtils.getDateToString(new Date().getTime(),new SimpleDateFormat("yyyy-MM-dd HH:mm")));
        tvOnlineDataEndTime.setText(DateUtils.getDateToString(new Date().getTime(),new SimpleDateFormat("yyyy-MM-dd HH:mm")));
        TimePickerDialogUtil.newInstence().setBeginToEndTime(this, tvOnlineDataBeginTime, tvOnlineDataEndTime, Type.ALL);

        niceSpinner.attachDataSource(dataset);
        niceSpinner.setSelectedIndex(0);
        niceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                datatype = dataTypes[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnOnlineDataSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getWaterData(PortListActivity.psCode, outputcode, datatype, outputtype, tvOnlineDataBeginTime.getText().toString(), tvOnlineDataEndTime.getText().toString(), pollutantcode);
            }
        });
    }


    @Override
    protected void initData() {
        mGesture = new GestureDetector(this, mOnGesture);
        outputcode = getIntent().getStringExtra("outputCode");
        typeName = getIntent().getStringExtra("typeName");

        switch (typeName) {
            case "污水":
                outputtype = "1";
                break;
            case "废气":
                outputtype = "gasApp";
                break;
            case "VOC":
                outputtype = "vocApp";
                break;
            case "空气质量":
                outputtype = "airApp";
                break;
            default:
                    outputtype = "";
        }

        llOnlineDataHead.setFocusable(true);
        llOnlineDataHead.setClickable(true);
        llOnlineDataHead.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());
//        presenter.getWaterData(PortListActivity.psCode, outputcode, "2061", outputtype, "2018-6-27 00:00"
//                , "2018-6-27 08:00", "0");
        presenter.getWaterData(PortListActivity.psCode, outputcode, datatype, outputtype, tvOnlineDataBeginTime.getText().toString(), tvOnlineDataEndTime.getText().toString(), pollutantcode);
//        presenter.getTestData();
    }

    /**
     * 手势
     */
    private GestureDetector.OnGestureListener mOnGesture = new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float vX, float vY) {
            if (e2.getX() - e1.getX() > 20) {
                flingState = FLING_LEFT;

            } else if (e1.getX() - e2.getX() > 20) {
                flingState = FLING_RIGHT;
            }
            return false;
        }

        /** 滚动 */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            flingState = FLING_CLICK;
            return false;
        }
    };


    @Override
    public void showError() {

    }

    @Override
    public void showSuccess() {

    }


    @Override
    public void showWaterData(DataWaterInfo dataWaterInfo) {
//        if(adapter==null){
//            adapter = new ListAdapter(this, dataWaterInfo.getRows(), head);
//            listview.setAdapter(adapter);
//        }else {
////            adapter.notifyDataSetChanged();
//        }
//        listview.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());
    }

    @Override
    public void showData(JSONArray jsonArray) {
        List<Map<String, String>> list = JsonUtil.jsonArrayToMapList(jsonArray);
        List<Map<String, String>> listChart = new ArrayList<>();

        for(int i=list.size()-1;i>=0;i--){
            listChart.add(list.get(i));
        }

        showFormData(list);
        showChartData(listChart);
    }

    private void showChartData(List<Map<String, String>> list) {
        List<List<Entry>> entriesList = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        List<String> dates = new ArrayList<>();

        for (Map.Entry<String, String> entry : list.get(0).entrySet()) {
            if (!entry.getKey().equals("监测时间")) {
                titles.add(entry.getKey());
                List<Entry> entries = new ArrayList<Entry>();
                for (int i = 0; i < list.size(); i++) {
                    String dateKey = list.get(i).get("监测时间");

                    String valueStr = list.get(i).get(entry.getKey());
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
                    entries.add(new Entry(i, value));
                }
                entriesList.add(entries);
            } else {
                for (int i = 0; i < list.size(); i++) {
                    String valueStr = list.get(i).get(entry.getKey());
                    dates.add(valueStr);
                }
            }
        }
        ChartViewUtil.showLineCharts(this, llOnlineDataCharts, entriesList, titles, dates);


    }

    private void showFormData(List<Map<String, String>> list) {
        if (adapter == null) {
            adapter = new ListAdapter(this, list, llOnlineDataHead);
            listview.setAdapter(adapter);
        } else {
//            listview.removeAllViews();
            adapter.notifyDataChanged(list);
        }
        listview.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());
    }


    @Override
    public void showEmpty() {

    }

    @Override
    public void setTimeListener() {

    }


    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    class ListViewAndHeadViewTouchLinstener implements View.OnTouchListener {

        float lastX = 0;
        float lastY = 0;
        private boolean isClick = false;
        private long downTime = 0;

        @Override
        public boolean onTouch(View arg0, MotionEvent ev) {
            //判断是否是点击
            float tempX = ev.getX();
            float tempY = ev.getY();
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    lastX = tempX;
                    lastY = tempY;
                    downTime = System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_MOVE:
                    //if(Math.abs(lastX - tempX) > 2 || Math.abs(lastY - tempY) > 2) {
                    //    isClick = false;
                    //    adapter.setTouchPosition(-1);
                    //}else {
                    // isClick = true;
                    //
                    break;
                case MotionEvent.ACTION_UP:
                    if (Math.abs(lastX - tempX) > 10 || Math.abs(lastY - tempY) > 10) {
                        isClick = false;
                        //   adapter.setTouchPosition(-1);
                    } else {
                        isClick = true;
                    }
                    long timeDef = System.currentTimeMillis() - downTime;
                    if (timeDef <= 100 && isClick && adapter.getTouchPosition() >= 0) {
//                        Toast.makeText(OnlineDataActivity.this, "position--->" + adapter.getTouchPosition(), Toast.LENGTH_SHORT).show();
                        isClick = false;
                        adapter.setTouchPosition(-1);
                    }
                    break;
            }

            // 当在列头 和 listView控件上touch时，将这个touch的事件分发给 ScrollView
            HorizontalScrollView headSrcrollView = (HorizontalScrollView) llOnlineDataHead.findViewById(R.id.head_scroll);
            headSrcrollView.onTouchEvent(ev);
            mGesture.onTouchEvent(ev);
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_data_online, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_dataOnline_change:
                if (svOnlineDataCharts.getVisibility() == View.VISIBLE) {
                    svOnlineDataCharts.setVisibility(View.GONE);
                    llOnlineDataHead.setVisibility(View.VISIBLE);
                    listview.setVisibility(View.VISIBLE);
                } else {
                    svOnlineDataCharts.setVisibility(View.VISIBLE);
                    llOnlineDataHead.setVisibility(View.GONE);
                    listview.setVisibility(View.GONE);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
