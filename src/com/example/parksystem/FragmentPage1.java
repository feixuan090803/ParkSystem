package com.example.parksystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.LauncherActivity.ListItem;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView.FindListener;
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
		
		String[] names = new String[]{ "我要停车", "次日续时", "快速充值", "泊位搜索","停车资讯"};
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
        return v;
    }
}
