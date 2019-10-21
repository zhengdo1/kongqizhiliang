package com.gz.xhb.Activity;


import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.gz.xhb.MVP.Presenter.MapPresenter;
import com.gz.xhb.MVP.View.MapInfoView;
import com.gz.xhb.R;
import com.gz.xhb.util.ToolBarUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xjj on 2018/6/5.
 */

public class AMapActivity extends XHBBaseActivity implements MapInfoView, GeocodeSearch.OnGeocodeSearchListener {
    MapView mapView;
    AMap aMap;
    private ArrayList<MarkerOptions> markerOptionlst = new ArrayList<MarkerOptions>();
    Map<String, Object> hashMap = new HashMap<>();
    private Marker currentMarker;
    private Location currentLocation;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    private MapPresenter mapPresenter = new MapPresenter(this);
    private CameraUpdate cameraUpdate;
    GeocodeSearch geocoderSearch;
    Map<String, com.gz.xhb.MVP.Model.Entity.Map> dataMap = new HashMap<>();

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_amap;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ToolBarUtil.setToolBar(this, "电子地图");
        mapView = (MapView) findViewById(R.id.mapView_amapHome);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);

        setMapView();
//        if(CheckPermissionUtil.hasPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)){
//            getLocation();
//        }else {
//            CheckPermissionUtil.getLocationPermissions(this,101);
//        }
        mapPresenter.getList();
    }

    private void setMapView() {
        if (aMap == null) {
            aMap = mapView.getMap();
//            aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 设置卫星地图模式，aMap是地图控制器对象。

            //控制地图缩放比例
//            aMap.moveCamera(CameraUpdateFactory.zoomTo(10));
            //设置logo的位置
            mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
            mUiSettings.setLogoPosition(AMapOptions.LOGO_MARGIN_RIGHT);


//            aMap.setLocationSource(this);//通过aMap对象设置定位数据源的监听
            mUiSettings.setMyLocationButtonEnabled(true); //显示默认的定位按钮
//            aMap.setMyLocationEnabled(true);// 可触发定位并显示当前位置
            //可视化区域，将指定位置指定到屏幕中心位置
            cameraUpdate = CameraUpdateFactory
                    .newCameraPosition(new CameraPosition(new LatLng(38.295000, 117.818333
                    ), 13, 0, 0));
            aMap.moveCamera(cameraUpdate);

//            geocoderSearch = new GeocodeSearch(this);
//            geocoderSearch.setOnGeocodeSearchListener(this);
//            // name表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode
//            GeocodeQuery query = new GeocodeQuery("深州市", "010");
//
//            geocoderSearch.getFromLocationNameAsyn(query);

            // 定义 Marker 点击事件监听
            AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
                // marker 对象被点击时回调的接口
                // 返回 true 则表示接口已响应事件，否则返回false
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Intent intent = new Intent(AMapActivity.this, StationDataDetailActivity.class);
                    intent.putExtra("data", (com.gz.xhb.MVP.Model.Entity.Map) dataMap.get(marker.getTitle()));
                    startActivity(intent);
                    currentMarker = marker;
                    return true;
                }
            };
            aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    if (currentMarker != null && currentMarker.isInfoWindowShown()) {
                        currentMarker.hideInfoWindow();//这个是隐藏infowindow窗口的方法
                    }
                }
            });

            // 绑定 Marker 被点击事件
            aMap.setOnMarkerClickListener(markerClickListener);

        }
    }


    private void addMarkersToMap(List<com.gz.xhb.MVP.Model.Entity.Map> psBeanList) {
        MarkerOptions[] option = new MarkerOptions[psBeanList.size()];
        for (int i = 0; i < psBeanList.size(); i++) {
            LatLng latLng;
            try {
                com.gz.xhb.MVP.Model.Entity.Map psBean = psBeanList.get(i);
                dataMap.put(psBean.getId(), psBean);

                Double latitude = Double.valueOf(psBean.getLatitude());
                Double longitude = Double.valueOf(psBean.getLongitude());
//                double[] lanlong = GPSUtil.bd09_To_Gcj02(latitude,longitude);
//                latLng = new LatLng(lanlong[0], lanlong[1]);
                latLng = new LatLng(latitude, longitude);

                option[i] = new MarkerOptions();
//                option[i].title(psBeanList.get(i).get());
                option[i].title(String.valueOf(psBean.getId()));
//                option[i].snippet("内容" + i);
//                CharSequence charSequence= Html.fromHtml(psBean.getContent());
//                option[i].snippet(charSequence.toString());

                option[i].position(latLng);
                if ("0".equals(psBean.getIsAirStation())) {
                    switch (psBean.getLevelid()) {
                        case "1":
                            option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.green1));//
                            break;
                        case "2":
                            option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.yellow1));//
                            break;
                        case "3":
                            option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.orange1));//
                            break;
                        case "4":
                            option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.red1));//
                            break;
                        case "5":
                            option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.darkred1));//
                            break;
                        case "6":
                            option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.black1));//
                    }
                } else if ("1".equals(psBean.getIsAirStation())) {
                    switch (psBean.getLevelid()) {
                        case "1":
                            option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.green2));//
                            break;
                        case "2":
                            option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.yellow2));//
                            break;
                        case "3":
                            option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.orange2));//
                            break;
                        case "4":
                            option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.red2));//
                            break;
                        case "5":
                            option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.darkred2));//
                            break;
                        case "6":
                            option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.black2));//
                    }
                }
            } catch (Exception e) {
                continue;
            }
            markerOptionlst.add(option[i]);
//            hashMap.put(psBeanList.get(i).getId(), psBeanList.get(i));
        }
        //一个一个增加地图缩放比例不失效
        for (int i = 0; i < markerOptionlst.size(); i++) {

            aMap.addMarker(markerOptionlst.get(i));

        }
        aMap.moveCamera(CameraUpdateFactory.zoomTo(13));
    }

    @Override
    public void showPsDot(List<com.gz.xhb.MVP.Model.Entity.Map> list) {
        addMarkersToMap(list);
    }

    @Override
    public void showError() {
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {


    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
//        double latitude = geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLatitude();
//        double longitude = geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLongitude();
//        //可视化区域，将指定位置指定到屏幕中心位置
//        cameraUpdate = CameraUpdateFactory
//                .newCameraPosition(new CameraPosition(new LatLng(latitude,
//                        longitude), 18, 0, 30));
//        aMap.moveCamera(cameraUpdate);
    }
}
