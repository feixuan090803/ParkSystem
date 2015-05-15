package com.example.parksystem;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;


public class FragmentPage4 extends Fragment
{
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment4_main, container, false);
		
		ScrollView sclView=(ScrollView)v.findViewById(R.id.person_list);
		
		RelativeLayout rl_accout=(RelativeLayout)v.findViewById(R.id.accout_item);
 		RelativeLayout rl_wallet=(RelativeLayout)v.findViewById(R.id.bank_item);
 		RelativeLayout rl_alarm=(RelativeLayout)v.findViewById(R.id.setting_item);
 		RelativeLayout rl_offline=(RelativeLayout)v.findViewById(R.id.offline_item);
 		RelativeLayout rl_induct=(RelativeLayout)v.findViewById(R.id.manual_item);
 		RelativeLayout rl_aboutus=(RelativeLayout)v.findViewById(R.id.about_item);
 		RelativeLayout rl_exit=(RelativeLayout)v.findViewById(R.id.quit_item);
 		
		rl_accout.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent_safe=new Intent(getActivity(),ToAccountSafe.class);
		       
				startActivity(intent_safe);
			}
		});
		
		rl_wallet.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent_wallet=new Intent(getActivity(),ToMyWallet.class);
		        
				startActivity(intent_wallet);
			}
		});
		
		rl_alarm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent_wallet=new Intent(getActivity(),ToAlarm.class);
		        
				startActivity(intent_wallet);
			}
		});
		
		rl_offline.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent_wallet=new Intent(getActivity(),ToOffline.class);
		        
				startActivity(intent_wallet);
			}
		});
		
		
		rl_exit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				new AlertDialog.Builder(getActivity()).setTitle("提示")//设置对话框标题  
			     .setMessage("确认退出当前账户？")//设置显示的内容  
			     .setPositiveButton("是",new DialogInterface.OnClickListener() {//添加确定按钮  
			         @Override  
			         public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件  			  
			             // TODO Auto-generated method stub  			  
			        	 Intent intent_wallet=new Intent(getActivity(),LoginActivity.class);
						 startActivity(intent_wallet); 			  
			         }  			  
			     }).setNegativeButton("否",new DialogInterface.OnClickListener() {//添加返回按钮  		  		  
			         @Override  		  
			         public void onClick(DialogInterface dialog, int which) {//响应事件  
			        	 return;
			         }  
			     }).show();//在按键响应事件中显示此对话框
			}
		});

		rl_induct.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent_wallet=new Intent(getActivity(),ToInduct.class);
		        
				startActivity(intent_wallet);
			}
		});
		
		rl_aboutus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent_wallet=new Intent(getActivity(),ToAboutUs.class);
		        
				startActivity(intent_wallet);
		}
	});
		
        return v;
    }
}
