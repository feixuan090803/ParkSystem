package com.example.parksystem;


import android.os.Bundle;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;  
import com.baidu.location.BDLocationListener;  
import com.baidu.location.LocationClient;  
import com.baidu.location.LocationClientOption;  
import com.baidu.mapapi.SDKInitializer;  
import com.baidu.mapapi.map.BaiduMap;  
import com.baidu.mapapi.map.BitmapDescriptor;  
import com.baidu.mapapi.map.MapStatusUpdate;  
import com.baidu.mapapi.map.MapStatusUpdateFactory;  
import com.baidu.mapapi.map.MapView;  
import com.baidu.mapapi.map.MyLocationData;  
import com.baidu.mapapi.model.LatLng;  

public class FragmentPage2 extends Fragment
{
	public MapView mMapView = null;  
    public BaiduMap baiduMap = null;  
  
    // 定位相关声明  
    public LocationClient locationClient = null;  
    //自定义图标  
    BitmapDescriptor mCurrentMarker = null;  
    boolean isFirstLoc = false;// 是否首次定位
    
    public BDLocationListener myListener = new BDLocationListener() {  
        @Override  
        public void onReceiveLocation(BDLocation location) {  
            // map view 销毁后不在处理新接收的位置  
            if (location == null || mMapView == null)  
                return;  
              
            MyLocationData locData = new MyLocationData.Builder()  
                    .accuracy(location.getRadius())  
                    // 此处设置开发者获取到的方向信息，顺时针0-360  
                    .direction(100).latitude(location.getLatitude())  
                    .longitude(location.getLongitude()).build();  
            baiduMap.setMyLocationData(locData);    //设置定位数据  
              
              
            if (isFirstLoc) {  
                isFirstLoc = false;  
                  
                  
                LatLng ll = new LatLng(location.getLatitude(),  
                        location.getLongitude());  
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 16);   //设置地图中心点以及缩放级别  
//              MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);  
                baiduMap.animateMapStatus(u);  
            }  
        }  
    }; 
    
    
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		SDKInitializer.initialize(getActivity().getApplicationContext());
		View v = inflater.inflate(R.layout.fragment2_main, container, false);
		
		// 获取地图控件引用
		//mMapView =(MapView)getActivity().findViewById(R.id.bmapView);
		mMapView =(MapView)v.findViewById(R.id.bmapView);
		baiduMap = mMapView.getMap();  
		
		//开启定位图层  
        baiduMap.setMyLocationEnabled(true);  
          
        locationClient = new LocationClient(getActivity().getApplicationContext()); // 实例化LocationClient类  
        locationClient.registerLocationListener(myListener); // 注册监听函数  
        this.setLocationOption();   //设置定位参数  
        locationClient.start(); // 开始定位  
        // baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); // 设置为一般地图  
  
        // baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE); //设置为卫星地图  
        // baiduMap.setTrafficEnabled(true); //开启交通图 
		
		
        return v;
    }
	
	@Override
	public void onPause() {  
        //MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()  
        mMapView.onPause();  
        super.onPause();  
    }  
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		//退出时销毁定位  
        locationClient.stop();  
        baiduMap.setMyLocationEnabled(false);
		
		super.onDestroy();
		mMapView.onDestroy(); 
		mMapView=null;
	}
	
	@Override
	public void onResume()  
    {  
        super.onResume();  
        // 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
        mMapView.onResume();  
    } 
	
	 /** 
     * 设置定位参数 
     */  
    private void setLocationOption() {  
        LocationClientOption option = new LocationClientOption();  
        option.setOpenGps(true); // 打开GPS  
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式  
        option.setCoorType("bd09ll"); // 返回的定位结果是百度经纬度,默认值gcj02  
        option.setScanSpan(5000); // 设置发起定位请求的间隔时间为5000ms  
        option.setIsNeedAddress(true); // 返回的定位结果包含地址信息  
        option.setNeedDeviceDirect(true); // 返回的定位结果包含手机机头的方向  
          
        locationClient.setLocOption(option);  
    }  
}
