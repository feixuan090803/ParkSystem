package com.example.parksystem;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

public class Main extends FragmentActivity
{
	// 定义FragmentTabHost对象
	
	private FragmentTabHost mTabHost;
	
	// 定义一个布局
	private LayoutInflater mInflater;
	
	// 定义数组来存放Fragment界面
	private Class mFragmentAry[] = {FragmentPage1.class,
	FragmentPage2.class, FragmentPage3.class, FragmentPage4.class };
	
	// 定义数组来存放按钮图片  
    private int mImgAry[] = { R.drawable.btn_home,  
            R.drawable.btn_near,  
            R.drawable.btn_service,  
            R.drawable.btn_my};  
	
	 // Tab选项卡的文字
    private String mTxtAry[] = { "首页", "附近", "服务", "我的" };
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_tab_layout);
 
        initView();
    }
    
    /**
     * 初始化组件
     */
    private void initView() 
    {
        // 实例化布局对象
        mInflater = LayoutInflater.from(this);
 
        // 实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
 
        // 得到fragment的个数
        int count = mFragmentAry.length;
 
        for (int i = 0; i < count; i++) {
            // 为每一个Tab按钮设置图标、文字和内容
            TabSpec tabSpec = mTabHost.newTabSpec(mTxtAry[i]).setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, mFragmentAry[i], null);
            // 设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.divider_color);
        }
    }
    
    /**
     * 给Tab按钮设置图标和文字
     * @param index
     * @return
     */
    private View getTabItemView(int index) 
    {
        View view = mInflater.inflate(R.layout.tab_item_view, null);
 
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImgAry[index]);
 
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTxtAry[index]);
 
        return view;
    }
}