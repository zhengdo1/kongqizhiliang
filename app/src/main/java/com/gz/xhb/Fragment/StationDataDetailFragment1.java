package com.gz.xhb.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gz.xhb.MVP.Model.Entity.Map;
import com.gz.xhb.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zdj on 2019/10/21.
 */
public class StationDataDetailFragment1 extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.tv_fragmentStationDataDetail_quality)
    TextView tvFragmentStationDataDetailQuality;
    @BindView(R.id.tv_fragmentStationDataDetail_name)
    TextView tvFragmentStationDataDetailName;
    @BindView(R.id.tv_fragmentStationDataDetail_time)
    TextView tvFragmentStationDataDetailTime;

    @BindView(R.id.tv_fragmentStationDataDetail_pm10)
    TextView tvFragmentStationDataDetailPm10;
    @BindView(R.id.tv_fragmentStationDataDetail_no2)
    TextView tvFragmentStationDataDetailNo2;
    @BindView(R.id.tv_fragmentStationDataDetail_co)
    TextView tvFragmentStationDataDetailCo;
    @BindView(R.id.tv_fragmentStationDataDetail_so2)
    TextView tvFragmentStationDataDetailSo2;
    @BindView(R.id.tv_fragmentStationDataDetail_o3)
    TextView tvFragmentStationDataDetailO3;
    @BindView(R.id.tv_fragmentStationDataDetail_pm25)
    TextView tvFragmentStationDataDetailPm25;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_station_data_detail1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        EventBus.getDefault().register(this);
        Bundle bundle = getArguments();
        Map mapData = (Map) bundle.getSerializable("data");
//        tvTest1.setText(Html.fromHtml(mapData.getContent()));

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        EventBus.getDefault().unregister(this);
        unbinder.unbind();

    }


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onGetMessage(Map message) {
//        tvTest1.setText(Html.fromHtml(message.getContent()));
//    }

}
