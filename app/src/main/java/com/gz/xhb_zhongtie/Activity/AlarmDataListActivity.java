package com.gz.xhb_zhongtie.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gz.xhb_zhongtie.Adapter.CommonListAdatper;
import com.gz.xhb_zhongtie.Base.BaseListActivity;
import com.gz.xhb_zhongtie.MVP.Model.Entity.PortInfoData;
import com.gz.xhb_zhongtie.MVP.Presenter.AlarmDataListPresenter;
import com.gz.xhb_zhongtie.MVP.View.AlarmDataListView;
import com.gz.xhb_zhongtie.R;
import com.gz.xhb_zhongtie.util.DateUtils;
import com.gz.xhb_zhongtie.util.TimePickerDialogUtil;
import com.gz.xhb_zhongtie.util.TimeUtil;
import com.gz.xhb_zhongtie.util.TitleBar;
import com.jzxiang.pickerview.data.Type;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by xjj on 2018/6/12.
 */

public class AlarmDataListActivity extends BaseListActivity<ListView, CommonListAdatper> implements AlarmDataListView {

    List<PortInfoData> list = new ArrayList<>();
    AlarmDataListPresenter presenter = new AlarmDataListPresenter(this);
    String keyword = "", outputtype = "1";
    List<String> dataset = new LinkedList<>(Arrays.asList("水", "气", "VOCs"));
    String[] dataTypes = new String[]{"1", "gasApp", "vocApp"};

    String typeName = "";
    @BindView(R.id.toolbar)
    TitleBar toolbar;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.toolbar_container)
    FrameLayout toolbarContainer;
    @BindView(R.id.tv_onlineData_beginTime)
    TextView tvOnlineDataBeginTime;
    @BindView(R.id.tv_onlineData_endTime)
    TextView tvOnlineDataEndTime;
    @BindView(R.id.ll_onlineData_time)
    LinearLayout llOnlineDataTime;
    @BindView(R.id.btn_onlineData_search)
    Button btnOnlineDataSearch;
    @BindView(R.id.ll_onlineData_condition)
    LinearLayout llOnlineDataCondition;
    @BindView(R.id.lv_baseList)
    ListView lvBaseList;
    @BindView(R.id.rl_baseList_refresh)
    BGARefreshLayout rlBaseListRefresh;


    @Override
    protected void beforeInit() {
        super.beforeInit();
        typeName = getIntent().getStringExtra("typeName");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_alarm_data_list;
    }

    @Override
    public String setTitle() {
        return typeName;
    }

    @Override
    public ListView setListView() {
        return findViewById(R.id.lv_baseList);
    }

//    @Override
//    public PortListAdatper setAdapter() {
//        return new PortListAdatper(this, list,null);
//    }

    @Override
    public void loadDataSync() {
    }

    void initSearchView() {
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                keyword = query;
                getData();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                keyword = newText;
                getData();
                return true;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    @Override
    protected void initData() {
//        niceSpinner.attachDataSource(dataset);
//        niceSpinner.setSelectedIndex(0);
//        niceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                outputtype = dataTypes[i];
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        btnOnlineDataSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
        tvOnlineDataBeginTime.setText(DateUtils.getDateToString(new Date().getTime(),new SimpleDateFormat("yyyy-MM-dd")));
        tvOnlineDataEndTime.setText(DateUtils.getDateToString(new Date().getTime(),new SimpleDateFormat("yyyy-MM-dd")));
        TimePickerDialogUtil.newInstence().setBeginToEndTime(this, tvOnlineDataBeginTime, tvOnlineDataEndTime, Type.YEAR_MONTH_DAY);
//        TimeUtil.setBeginToEndTime(this, tvOnlineDataBeginTime, tvOnlineDataEndTime);
        initSearchView();

        getData();
//        presenter.getTestData();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        startActivity(new Intent(this,WaterDataActivity.class));
//        startActivity(new Intent(this,OnlineDataActivity.class));
    }

    private void getData() {
        if (typeName.equals("报警数据")) {
            presenter.getAlarmDataList100(keyword, outputtype, tvOnlineDataBeginTime.getText().toString(), tvOnlineDataEndTime.getText().toString());
        } else if (typeName.equals("预警数据")) {
            presenter.getAlarmDataList80(keyword, outputtype, tvOnlineDataBeginTime.getText().toString(), tvOnlineDataEndTime.getText().toString());
        }
    }

    @Override
    public void showList(List<JSONObject> list) {
        if (adapter == null) {
            adapter = new CommonListAdatper(this, list);
            listView.setAdapter(adapter);
        } else {
//            listview.removeAllViews();
            adapter.notifyDataChanged(list);
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void updateListView(List<JSONObject> list) {

    }


    @Override
    public void showSuccess() {

    }


    @Override
    public void endRefresh() {

    }

    @Override
    public void endLoadMore() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ps_list, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }
}
