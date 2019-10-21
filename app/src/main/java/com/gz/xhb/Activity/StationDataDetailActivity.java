package com.gz.xhb.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;

import com.gz.xhb.Fragment.StationDataDetailFragment1;
import com.gz.xhb.Fragment.StationDataDetailFragment2;
import com.gz.xhb.MVP.Model.Entity.Map;
import com.gz.xhb.R;
import com.gz.xhb.util.ToolBarUtil;

import butterknife.BindView;

/**
 * Created by zdj on 2019/10/21.
 */
public class StationDataDetailActivity extends XHBBaseActivity {
    @BindView(R.id.ll_stationDataDetail1)
    LinearLayout llStationDataDetail1;
    @BindView(R.id.ll_stationDataDetail2)
    LinearLayout llStationDataDetail2;
    StationDataDetailFragment1 fragment1 = new StationDataDetailFragment1();
    StationDataDetailFragment2 fragment2 = new StationDataDetailFragment2();

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
        fragmentTransaction.add(R.id.ll_stationDataDetail1, fragment1);
        fragmentTransaction.add(R.id.ll_stationDataDetail2, fragment2);
        fragmentTransaction.commit();
    }
}
