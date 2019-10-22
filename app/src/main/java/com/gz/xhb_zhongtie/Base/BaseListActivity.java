package com.gz.xhb_zhongtie.Base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gz.xhb_zhongtie.Activity.XHBBaseActivity;
import com.gz.xhb_zhongtie.R;
import com.gz.xhb_zhongtie.util.ToolBarUtil;

import java.util.List;

/**
 * Created by xjj on 2018/6/12.
 */

public abstract class BaseListActivity<LV extends ListView, A extends XHBBaseAdapter> extends XHBBaseActivity implements AdapterView.OnItemClickListener{
    public LV listView;
//    List<T> mList = new ArrayList<>();
    public A adapter;



    @Override
    protected void initView(Bundle savedInstanceState) {
        ToolBarUtil.setToolBar(this,setTitle());
        listView = setListView();
//        adapter = setAdapter();
//        listView.setAdapter(adapter);
//        loadDataSync();
        listView.setOnItemClickListener(this);
    }
    public abstract String setTitle();
    public abstract LV setListView();
    public void setAdapter(A adapter){
        this.adapter = adapter;
    };

    public abstract void loadDataSync();

    protected  void onLoadSuccess(List list){
        adapter.onDateChange(list);
    }



}
