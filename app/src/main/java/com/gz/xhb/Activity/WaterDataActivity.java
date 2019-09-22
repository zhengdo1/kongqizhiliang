//package com.gz.xhb.Activity;
//
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ListView;
//
//import com.gz.xhb.Base.BaseOnlineDataActivity;
//import com.gz.xhb.MVP.Model.Entity.OnlineDataInfo;
//import com.gz.xhb.MVP.Model.Entity.DataWaterInfo;
//import com.gz.xhb.R;
//import com.gz.xhb.util.UIUtil;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by xjj on 2018/6/13.
// */
//
//public class WaterDataActivity extends BaseOnlineDataActivity<DataWaterInfo> {
//    List<OnlineDataInfo<DataWaterInfo>> list = new ArrayList<>();
//
//    @Override
//    protected int getContentViewLayoutID() {
//        return R.layout.activity_data;
//    }
//
//
//    @Override
//    public String setTitle() {
//        return "水监控点";
//    }
//
//    @Override
//    public ListView setListView() {
//        return findViewById(R.id.lv_baseList);
//    }
//
////    @Override
////    public XHBBaseAdapter setAdapter() {
////        return new WaterDataAdapter(this,list,null);
////    }
//
//    @Override
//    public void loadDataSync() {
//        if(list == null||list.size()==0){
//            list.add(initTitle());
//        }
//        for(int i=0;i<30;i++){
//            DataWaterInfo dataWaterInfo = new DataWaterInfo();
//            dataWaterInfo.setTest1("2017-6-13 16:37:5"+i);
//            dataWaterInfo.setTest2(30+i+"");
//            dataWaterInfo.setTest3(20+i+"");
//            dataWaterInfo.setTest4(10+i+"");
//            OnlineDataInfo<DataWaterInfo> onlineDataInfo = new OnlineDataInfo<>();
//            onlineDataInfo.setT(dataWaterInfo);
//            if(i%2==0){
//                onlineDataInfo.setColor(getResources().getColor(R.color.colorAccent));
//            }else {
//                onlineDataInfo.setColor(getResources().getColor(R.color.backgroud_color));
//            }
//            onlineDataInfo.setHeight(-1);
//            list.add(onlineDataInfo);
//        }
//    }
//
//    private OnlineDataInfo<DataWaterInfo> initTitle() {
//        DataWaterInfo dataWaterInfo = new DataWaterInfo();
//        dataWaterInfo.setTest1("时间");
//        dataWaterInfo.setTest2("COD");
//        dataWaterInfo.setTest3("氨氮");
//        dataWaterInfo.setTest4("流量");
//        OnlineDataInfo<DataWaterInfo> onlineDataInfo = new OnlineDataInfo<>();
//        onlineDataInfo.setT(dataWaterInfo);
//        onlineDataInfo.setColor(getResources().getColor(R.color.colorPrimaryDark));
//        onlineDataInfo.setHeight(UIUtil.dp2px(this,50));
//        return onlineDataInfo;
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//    }
//}
