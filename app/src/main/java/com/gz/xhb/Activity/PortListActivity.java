package com.gz.xhb.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gz.xhb.Adapter.PortListAdatper;
import com.gz.xhb.Base.BaseListActivity;
import com.gz.xhb.MVP.Model.Entity.PortInfo;
import com.gz.xhb.MVP.Model.Entity.PortInfoData;
import com.gz.xhb.MVP.Presenter.PortInfoListPresenter;
import com.gz.xhb.MVP.View.PortInfoListView;
import com.gz.xhb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xjj on 2018/6/12.
 */

public class PortListActivity extends BaseListActivity<ListView, PortListAdatper> implements PortInfoListView{
    List<PortInfoData> list = new ArrayList<>();
    PortInfoListPresenter presenter = new PortInfoListPresenter(this);
    public static String psCode,typeName;
    private String outputtype = "0";
    private boolean jumpToDataDetail;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_list;
    }

    @Override
    public String setTitle() {
        String title = getIntent().getStringExtra("psName");
        if(title!=null){
            return title;
        }
        return "";
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

    @Override
    protected void initData() {
         typeName = getIntent().getStringExtra("typeName");
         psCode = getIntent().getStringExtra("psCode");
         jumpToDataDetail = getIntent().getBooleanExtra("jumpToDataDetail",false);
        switch (typeName) {
            case "污水":
                outputtype = "1";
                break;
            case "废气":
                outputtype = "2";
                break;
            case "VOC":
                outputtype = "3";
                break;
                default:
                    outputtype = "";
        }

        presenter.getList(psCode,outputtype);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        startActivity(new Intent(this,WaterDataActivity.class));
//        startActivity(new Intent(this,OnlineDataActivity.class));
    }

    @Override
    public void showList(List<PortInfoData> list) {
        if(adapter==null){
            if(jumpToDataDetail){
                setAdapter(new PortListAdatper(this, list,OnlineDataActivity.class));
            }else {
                setAdapter(new PortListAdatper(this, list,null));
            }
        }
        listView.setAdapter(adapter);

    }

    @Override
    public void showError() {

    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void updateListView(List<PortInfoData> list) {
        adapter.onDateChange(list);
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
}
