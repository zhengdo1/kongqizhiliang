package com.gz.xhb_zhongtie.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gz.xhb_zhongtie.MVP.Model.Entity.Map;
import com.gz.xhb_zhongtie.R;

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

        String levelid = mapData.getLevelid();
        String outPutName = mapData.getOutputname();
        String monitorTime = mapData.getMonitortime();
        String pm25 = mapData.getValue_D13();
        String pm10 = mapData.getValue_D12();
        String no2 = mapData.getValue_D03();
        String co = mapData.getValue_D07();
        String so2 = mapData.getValue_D10();
        String o3 = mapData.getValue_D16();

        while (pm25!=null&&pm25!=""&&(pm25.contains(".") && pm25.endsWith("0")) || pm25.endsWith(".")) {
           pm25 =  pm25.substring(0, pm25.length()-1);
        }
        while (pm10!=null&&pm10!=""&&(pm10.contains(".") && pm10.endsWith("0")) || pm10.endsWith(".")) {
            pm10 =  pm10.substring(0, pm10.length()-1);
        }
        while (no2!=null&&no2!=""&&(no2.contains(".") && no2.endsWith("0")) || no2.endsWith(".")) {
            no2 =  no2.substring(0, no2.length()-1);
        }
        while (co!=null&&co!=""&&(co.contains(".") && co.endsWith("0")) || co.endsWith(".")) {
            co =  co.substring(0, co.length()-1);
        }
        while (so2!=null&&so2!=""&&(so2.contains(".") && so2.endsWith("0")) || so2.endsWith(".")) {
            so2 =  so2.substring(0, so2.length()-1);
        }
        while (o3!=null&&o3!=""&&(o3.contains(".") && o3.endsWith("0")) || o3.endsWith(".")) {
            o3 =  o3.substring(0, o3.length()-1);
        }
        tvFragmentStationDataDetailName.setText(outPutName);
        tvFragmentStationDataDetailTime.setText(monitorTime);
        tvFragmentStationDataDetailPm25.setText(pm25);
        tvFragmentStationDataDetailPm10.setText(pm10);
        tvFragmentStationDataDetailNo2.setText(no2);
        tvFragmentStationDataDetailCo.setText(co);
        tvFragmentStationDataDetailSo2.setText(so2);
        tvFragmentStationDataDetailO3.setText(o3);

        setBg(tvFragmentStationDataDetailPm25,pm25);
        setBg(tvFragmentStationDataDetailPm10,pm10);
        setBg(tvFragmentStationDataDetailNo2,no2);
        setBg(tvFragmentStationDataDetailCo,co);
        setBg(tvFragmentStationDataDetailSo2,so2);
        setBg(tvFragmentStationDataDetailO3,o3);

        if (levelid != null) {
            switch (levelid) {
                case "1":
                    tvFragmentStationDataDetailQuality.setText("优");
                    tvFragmentStationDataDetailQuality.setBackgroundColor(getResources().getColor(R.color.color_map1));
                    break;
                case "2":
                    tvFragmentStationDataDetailQuality.setText("良");
                    tvFragmentStationDataDetailQuality.setBackgroundColor(getResources().getColor(R.color.color_map2));
                    break;
                case "3":
                    tvFragmentStationDataDetailQuality.setText("轻度污染");
                    tvFragmentStationDataDetailQuality.setBackgroundColor(getResources().getColor(R.color.color_map3));
                    break;
                case "4":
                    tvFragmentStationDataDetailQuality.setText("中度污染");
                    tvFragmentStationDataDetailQuality.setBackgroundColor(getResources().getColor(R.color.color_map4));
                    break;
                case "5":
                    tvFragmentStationDataDetailQuality.setText("重度污染");
                    tvFragmentStationDataDetailQuality.setBackgroundColor(getResources().getColor(R.color.color_map5));
                    break;
                case "6":
                    tvFragmentStationDataDetailQuality.setText("严重污染");
                    tvFragmentStationDataDetailQuality.setBackgroundColor(getResources().getColor(R.color.color_map6));
                    break;
                default:
//                    tvFragmentStationDataDetailQuality.setText("优");
//                    tvFragmentStationDataDetailQuality.setBackgroundColor(getResources().getColor(R.color.color_map1));

            }
        }
    }

    private void setBg(TextView textView, String valueStr) {
        try {
            double value = Double.parseDouble(valueStr);
            if(value<=50.0){
                textView.setBackgroundColor(getResources().getColor(R.color.color_map1));
            }else if(50.0<value&&value<=100.0){
                textView.setBackgroundColor(getResources().getColor(R.color.color_map2));
            }else if(100.0<value&&value<=150.0){
                textView.setBackgroundColor(getResources().getColor(R.color.color_map3));
            }else if(150.0<value&&value<=200.0){
                textView.setBackgroundColor(getResources().getColor(R.color.color_map4));
            }else if(200.0<value&&value<=300.0){
                textView.setBackgroundColor(getResources().getColor(R.color.color_map5));
            }else if(300.0<value){
                textView.setBackgroundColor(getResources().getColor(R.color.color_map6));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
