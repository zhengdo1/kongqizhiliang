package com.gz.xhb.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DebugUtils;
import android.widget.LinearLayout;

import com.gz.xhb.Fragment.StationDataDetailFragment1;
import com.gz.xhb.Fragment.StationDataDetailFragment2;
import com.gz.xhb.MVP.Model.Entity.Map;
import com.gz.xhb.MVP.Model.Entity.MapStationDataDetail;
import com.gz.xhb.MVP.Presenter.MapPresenter;
import com.gz.xhb.MVP.Presenter.MapStationDataDetailPresenter;
import com.gz.xhb.MVP.View.StationDataDetailView;
import com.gz.xhb.R;
import com.gz.xhb.util.ToolBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zdj on 2019/10/21.
 */
public class StationDataDetailActivity extends XHBBaseActivity implements StationDataDetailView {
    @BindView(R.id.ll_stationDataDetail1)
    LinearLayout llStationDataDetail1;
    @BindView(R.id.ll_stationDataDetail2)
    LinearLayout llStationDataDetail2;
    StationDataDetailFragment1 fragment1 = new StationDataDetailFragment1();
    StationDataDetailFragment2 fragment2 = new StationDataDetailFragment2();
    private MapStationDataDetailPresenter mPresenter = new MapStationDataDetailPresenter(this);

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_station_data_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();
        Map mapData = (Map) intent.getSerializableExtra("data");
        ToolBarUtil.setToolBar(this, mapData.getOutputname());

//        EventBus.getDefault().post(mapData);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", mapData);
        fragment1.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ll_stationDataDetail1, fragment1);
        fragmentTransaction.commit();

        String psCode = mapData.getPscode();
        String outputcode = mapData.getOutputcode();
        String isToutput = mapData.getIsToutput();
        mPresenter.getMapStationDataDetail(psCode,outputcode,isToutput);
    }

    @Override
    public void showData1() {

    }

    @Override
    public void showData2(List<MapStationDataDetail> mapStationDataDetails) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) mapStationDataDetails);
        fragment2.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ll_stationDataDetail2, fragment2);
        fragmentTransaction.commit();
    }


    @Override
    public void showError() {

    }

    @Override
    public void showSuccess() {

    }
}
