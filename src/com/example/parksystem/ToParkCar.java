package com.example.parksystem;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import com.example.parksystem.PickerView.onSelectListener;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu; 
import android.view.View.OnClickListener;
import android.text.Editable;  
import android.text.TextWatcher; 

public class ToParkCar extends Activity 
{
	
	//车位编号
	EditText et1,et2,et3,et4,et5,et6;
	
	Button btnConfirm;
	
	//消费状态显示（应付金额，当前余额）
	TextView tvAmountPay,tvAmountRemain;
	TextView tvTimeSelect,tvParkToPay;
	//计算得到的应付金额
	float amountPay;
	
	TextView tvReturn;

	PickerView hour_pv;  
    PickerView minute_pv;  
	
    //用户选择的停车时间
    String strHour="00",strMinute="00",strTime;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.topark);
        
        //绑定车位编号 编辑框
        et1 = (EditText)findViewById(R.id.parknum_1);
        et2 = (EditText)findViewById(R.id.parknum_2);
        et3 = (EditText)findViewById(R.id.parknum_3);
        et4 = (EditText)findViewById(R.id.parknum_4);
        et5 = (EditText)findViewById(R.id.parknum_5);
        et6 = (EditText)findViewById(R.id.parknum_6);
        
        
        //绑定确认按钮
        btnConfirm= (Button)findViewById(R.id.park_confirm);
        
        //绑定返回键
        tvReturn=(TextView)findViewById(R.id.title_return);
        
        //绑定选择时长显示视图
        tvTimeSelect=(TextView)findViewById(R.id.park_time_select);
        
        //绑定应付金额显示视图
        tvParkToPay=(TextView)findViewById(R.id.park_to_pay);
        ////////////////////////////////
        //滚动条选择停车时间
        hour_pv = (PickerView) findViewById(R.id.hour_pv);  
        minute_pv = (PickerView) findViewById(R.id.minute_pv);  
        List<String> hours = new ArrayList<String>();  
        List<String> minutes = new ArrayList<String>();  
        for (int i = 0; i < 12; i++)  
        {  
            hours.add(i < 10 ? "0" + i : "" + i);  
        }  
         
	     minutes.add("00");
	     minutes.add("10");
	     minutes.add("20");
	     minutes.add("30");
	     minutes.add("40");
	     minutes.add("50");
       
         hour_pv.setData(hours);  
         hour_pv.setOnSelectListener(new onSelectListener()  
         {  
  
            @Override  
            public void onSelect(String text)  
            {  
                Toast.makeText(ToParkCar.this, "您选择了 " + text + " 小时",  
                        Toast.LENGTH_SHORT).show();
                strHour=text;
                strTime=String.format("%s 小时   %s 分钟",strHour,strMinute);
                tvTimeSelect.setText(strTime);
                
                CalculateAmount(tvParkToPay,strHour,strMinute);
            }
         });  
         minute_pv.setData(minutes);  
         minute_pv.setOnSelectListener(new onSelectListener()  
         {  
  
            @Override  
            public void onSelect(String text)  
            {  
                Toast.makeText(ToParkCar.this, "您选择了 " + text + " 分钟",  
                        Toast.LENGTH_SHORT).show(); 
                strMinute=text;
                strTime=String.format("%s 小时   %s 分钟",strHour,strMinute);
                tvTimeSelect.setText(strTime);
                
                CalculateAmount(tvParkToPay,strHour,strMinute);
            }  
         });
        
        //为编号框绑定监听器，实现编辑框自动跳转
        et1.addTextChangedListener(new watcher(et1));
        et2.addTextChangedListener(new watcher(et2));
        et3.addTextChangedListener(new watcher(et3));
        et4.addTextChangedListener(new watcher(et4));
        et5.addTextChangedListener(new watcher(et5));
        et6.addTextChangedListener(new watcher(et6));
        ///////////////////////////////
        
        tvReturn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ToParkCar.this,Main.class);
				startActivity(intent);
				
				finish();
			}
		});
	}
	
	public class watcher implements TextWatcher
	{
			
		 	private EditText etID=null;
	
		 	public watcher(EditText id)
		 	{
		 		etID=id;
		 	}
		 	
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) 
			{
				// TODO Auto-generated method stub
				if(etID == et1)
				{		
					GetFoucus(et2);
					
				}
				else if( etID == et2)
				{	
					GetFoucus(et3);
				}
				else if(etID == et3)
				{
					GetFoucus(et4);
				}
				else if( etID == et4)
				{
					GetFoucus(et5);
				}
				else if(etID == et5)
				{
					GetFoucus(et6);
				}
				else if(etID == et6)
				{
					if(etID.getText().length()==0)
					{
						et1.setText("");
						et2.setText("");
						et3.setText("");
						et4.setText("");
						et5.setText("");
						GetFoucus(et1);
					}
				}
			}
	}
	
	//使编辑框重获焦点
	public void GetFoucus(EditText et)
	{
		et.setFocusable(true);
		et.setFocusableInTouchMode(true);
		et.requestFocus();
		et.findFocus();
	}
	
	//使编辑框重获焦点
	public void CalculateAmount(TextView tv,String strHour,String strMinute)
	{
		int iHour=Integer.parseInt(strHour);
		int iMinute=Integer.parseInt(strMinute);
		
		//停车收费为每十分钟2.5块
		double fPerTenMinute=2.5;
		double dSumPay=fPerTenMinute*(6*iHour+iMinute/10.0);
		
		String strSumPay=Double.toString(dSumPay)+" 元";
		tv.setText(strSumPay);
	}

}

