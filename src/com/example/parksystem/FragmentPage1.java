package com.example.parksystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.LauncherActivity.ListItem;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

//首页
public class FragmentPage1 extends Fragment
{
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) 
	{
		View v = inflater.inflate(R.layout.fragment1_main, container, false);
		ListView listView=(ListView)v.findViewById(R.id.mylist); //此句不能放在最后，否则不显示
		
		String spacing=getResources().getString(R.string.label_spacing);
		String[] names = new String[]{ "我要停车"+spacing, 
									   "次日续时"+spacing, 
									   "快速充值"+spacing, 
									   "泊位搜索"+spacing,
									   "停车资讯"+spacing};
		String[] descs = new String[]{ "停好车后，点击此处完成缴费", "次日停车位提前申请", "随时随地，想冲就冲", "搜索深圳市区所有路边停车位","第一时间获取最权威的停车资讯"};
		int[] imageIds = new int[]
			{ R.drawable.f11 , R.drawable.f12
				, R.drawable.f13, R.drawable.f14,R.drawable.f15};
			
		//创建一个List集合，List集合的元素是Map
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < names.length; i++)
		{
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("header", imageIds[i]);
			listItem.put("personName", names[i]);
			listItem.put("desc",descs[i]);
			listItems.add(listItem);
		} 
		
		//创建一个SimpleAdapter
		SimpleAdapter simpleAdapter = new SimpleAdapter(
			getActivity()
			, listItems 
			, R.layout.fragment1
			, new String[]{ "personName", "header","desc" }
			, new int[]{R.id.name , R.id.header,R.id.desc});
		
		listView.setAdapter(simpleAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
            	switch (position)
            	{
				case 0:
					Intent intent_park=new Intent(getActivity(),ToParkCar.class);
					startActivity(intent_park);
					
					
					break;
				case 1:
			
					Intent book_park=new Intent(getActivity(),ToBookPark.class);
					startActivity(book_park);
					
					break;
				case 2:
					DialogUtil.showDialog(getActivity(), "点击了第"+(position+1)+"个条目", false);
					break;
				case 3:
					DialogUtil.showDialog(getActivity(), "点击了第"+(position+1)+"个条目", false);
					break;
				case 4:
					DialogUtil.showDialog(getActivity(), "点击了第"+(position+1)+"个条目", false);
					break;
				default:
					break;
				}
            }
        });
		
		return v;
    }
}
