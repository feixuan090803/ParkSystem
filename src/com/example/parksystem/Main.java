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
	// ����FragmentTabHost����
	
	private FragmentTabHost mTabHost;
	
	// ����һ������
	private LayoutInflater mInflater;
	
	// �������������Fragment����
	private Class mFragmentAry[] = {FragmentPage1.class,
	FragmentPage2.class, FragmentPage3.class, FragmentPage4.class };
	
	// ������������Ű�ťͼƬ  
    private int mImgAry[] = { R.drawable.btn_home,  
            R.drawable.btn_near,  
            R.drawable.btn_service,  
            R.drawable.btn_my};  
	
	 // Tabѡ�������
    private String mTxtAry[] = { "��ҳ", "����", "����", "�ҵ�" };
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_tab_layout);
 
        initView();
    }
    
    /**
     * ��ʼ�����
     */
    private void initView() 
    {
        // ʵ�������ֶ���
        mInflater = LayoutInflater.from(this);
 
        // ʵ����TabHost���󣬵õ�TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
 
        // �õ�fragment�ĸ���
        int count = mFragmentAry.length;
 
        for (int i = 0; i < count; i++) {
            // Ϊÿһ��Tab��ť����ͼ�ꡢ���ֺ�����
            TabSpec tabSpec = mTabHost.newTabSpec(mTxtAry[i]).setIndicator(getTabItemView(i));
            // ��Tab��ť��ӽ�Tabѡ���
            mTabHost.addTab(tabSpec, mFragmentAry[i], null);
            // ����Tab��ť�ı���
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.divider_color);
        }
    }
    
    /**
     * ��Tab��ť����ͼ�������
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