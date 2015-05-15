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
  
    // ��λ�������  
    public LocationClient locationClient = null;  
    //�Զ���ͼ��  
    BitmapDescriptor mCurrentMarker = null;  
    boolean isFirstLoc = false;// �Ƿ��״ζ�λ
    
    public BDLocationListener myListener = new BDLocationListener() {  
        @Override  
        public void onReceiveLocation(BDLocation location) {  
            // map view ���ٺ��ڴ����½��յ�λ��  
            if (location == null || mMapView == null)  
                return;  
              
            MyLocationData locData = new MyLocationData.Builder()  
                    .accuracy(location.getRadius())  
                    // �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360  
                    .direction(100).latitude(location.getLatitude())  
                    .longitude(location.getLongitude()).build();  
            baiduMap.setMyLocationData(locData);    //���ö�λ����  
              
              
            if (isFirstLoc) {  
                isFirstLoc = false;  
                  
                  
                LatLng ll = new LatLng(location.getLatitude(),  
                        location.getLongitude());  
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 16);   //���õ�ͼ���ĵ��Լ����ż���  
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
		
		// ��ȡ��ͼ�ؼ�����
		//mMapView =(MapView)getActivity().findViewById(R.id.bmapView);
		mMapView =(MapView)v.findViewById(R.id.bmapView);
		baiduMap = mMapView.getMap();  
		
		//������λͼ��  
        baiduMap.setMyLocationEnabled(true);  
          
        locationClient = new LocationClient(getActivity().getApplicationContext()); // ʵ����LocationClient��  
        locationClient.registerLocationListener(myListener); // ע���������  
        this.setLocationOption();   //���ö�λ����  
        locationClient.start(); // ��ʼ��λ  
        // baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); // ����Ϊһ���ͼ  
  
        // baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE); //����Ϊ���ǵ�ͼ  
        // baiduMap.setTrafficEnabled(true); //������ͨͼ 
		
		
        return v;
    }
	
	@Override
	public void onPause() {  
        //MapView������������Activityͬ������activity����ʱ�����MapView.onPause()  
        mMapView.onPause();  
        super.onPause();  
    }  
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		//�˳�ʱ���ٶ�λ  
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
        // ��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onResume();  
    } 
	
	 /** 
     * ���ö�λ���� 
     */  
    private void setLocationOption() {  
        LocationClientOption option = new LocationClientOption();  
        option.setOpenGps(true); // ��GPS  
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// ���ö�λģʽ  
        option.setCoorType("bd09ll"); // ���صĶ�λ����ǰٶȾ�γ��,Ĭ��ֵgcj02  
        option.setScanSpan(5000); // ���÷���λ����ļ��ʱ��Ϊ5000ms  
        option.setIsNeedAddress(true); // ���صĶ�λ���������ַ��Ϣ  
        option.setNeedDeviceDirect(true); // ���صĶ�λ��������ֻ���ͷ�ķ���  
          
        locationClient.setLocOption(option);  
    }  
}
