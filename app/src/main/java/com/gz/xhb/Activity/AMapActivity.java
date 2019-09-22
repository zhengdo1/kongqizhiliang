package com.gz.xhb.Activity;


import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;

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
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.gz.xhb.MVP.Presenter.MapPresenter;
import com.gz.xhb.MVP.View.MapInfoView;
import com.gz.xhb.R;
import com.gz.xhb.util.CheckPermissionUtil;
import com.gz.xhb.util.ToolBarUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xjj on 2018/6/5.
 */

public class AMapActivity extends XHBBaseActivity implements MapInfoView ,GeocodeSearch.OnGeocodeSearchListener {
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
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_amap;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ToolBarUtil.setToolBar(this,"电子地图");
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
                    .newCameraPosition(new CameraPosition(new LatLng(38.0002200000,115.5600100000
                           ), 10, 0, 30));
            aMap.moveCamera(cameraUpdate);

//            geocoderSearch = new GeocodeSearch(this);
//            geocoderSearch.setOnGeocodeSearchListener(this);
//            // name表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode
//            GeocodeQuery query = new GeocodeQuery("深州市", "010");
//
//            geocoderSearch.getFromLocationNameAsyn(query);

        }
    }

//    public void getLocation() {
//        MyLocationStyle myLocationStyle;
//        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;//定位一次，且将视角移动到地图中心点。
////        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。。
////        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
//        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
//        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
//    }

    private void addMarkersToMap(List<com.gz.xhb.MVP.Model.Entity.Map> psBeanList) {
        MarkerOptions[] option = new MarkerOptions[psBeanList.size()];
        for (int i = 0; i < psBeanList.size(); i++) {
            LatLng latLng;
            try {
                com.gz.xhb.MVP.Model.Entity.Map psBean = psBeanList.get(i);
                Double latitude = Double.valueOf(psBean.getLatitude());
                Double longitude = Double.valueOf(psBean.getLongitude());
//                double[] lanlong = GPSUtil.bd09_To_Gcj02(latitude,longitude);
//                latLng = new LatLng(lanlong[0], lanlong[1]);
                latLng = new LatLng(latitude, longitude);

                option[i] = new MarkerOptions();
//                option[i].title(psBeanList.get(i).get());
//                option[i].title("企业"+i);
//                option[i].snippet("内容" + i);
                CharSequence charSequence= Html.fromHtml(psBean.getContent());
                option[i].snippet(charSequence.toString());
                option[i].position(latLng);
                option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker1));//正常

//                switch (psBean.getState()) {
//                    case "1":
//                        option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker1));//正常
//                        break;
//                    case "2":
//                        option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker2));//超标
//                        break;
//                    case "3":
//                        option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker3));//异常
//                        break;
//                    case "4":
//                        option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker4));//离线
//                        break;
////                    case "5":
////                        option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker5));//超标+异常 没有超标+异常
////                        break;
//                    default:
//                        option[i].icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker1));//正常+
//                }
            } catch (Exception e) {
                continue;
            }
            markerOptionlst.add(option[i]);
//            hashMap.put(psBeanList.get(i).getId(), psBeanList.get(i));
        }
//        mList = aMap.addMarkers(markerOptionlst, true);
//        LocationManager locationManager = (LocationManager) rootView.getContext().getSystemService(LOCATION_SERVICE);//获得位置服务
//        String provider = judgeProvider(locationManager);
//        if (provider != null) {//有位置提供器的情况
//            //为了压制getLastKnownLocation方法的警告
//            if (ActivityCompat.checkSelfPermission(rootView.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(rootView.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
//            Location location = locationManager.getLastKnownLocation(provider);
//            LatLng target = new LatLng(location.getLatitude(), location.getLongitude());
//            addFilterMarkers(markerOptionlst, target);
        //一个一个增加地图缩放比例不失效
        for (int i = 0; i < markerOptionlst.size(); i++) {

            aMap.addMarker(markerOptionlst.get(i));

        }
        aMap.moveCamera(CameraUpdateFactory.zoomTo(10));
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
