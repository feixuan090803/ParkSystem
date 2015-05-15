package com.example.parksystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FindPwd extends Activity{
	Button btnConfirmNum,btnConfirmReg;
	EditText etFindName,etFindPass,etFindConfirmPass,etFindConfirmNum;
	TextView tvReturn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tofindpwd);
        
        //绑定手机验证按钮
        btnConfirmNum=(Button)findViewById(R.id.find_confirm_num);
        
        //绑定确认修改按钮
        btnConfirmReg=(Button)findViewById(R.id.find_confirm_register);
        //btnConfirmReg.setEnabled(false); //初始化为不可用
        
        
        //绑定 要修改密码的手机号 编辑框
        etFindName=(EditText)findViewById(R.id.find_username_edit);
        
        //绑定 手机验证码 编辑框
        etFindConfirmNum=(EditText)findViewById(R.id.find_numconfirm_edit);
        
        //绑定新设置密码编辑框
        etFindPass=(EditText)findViewById(R.id.find_password_edit);
        
        //绑定密码验证编辑框
        etFindConfirmPass=(EditText)findViewById(R.id.find_pwdconfirm_edit);
        
        //绑定返回登陆界面按钮
        tvReturn=(TextView)findViewById(R.id.find_title_return);
        
        //为确认修改按钮绑定事件监听器
        btnConfirmReg.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(validate())
				{
					DialogUtil.showDialog(FindPwd.this,"修改成功,请重新登录！",false);	
					//若注册成功，返回登录界面
					Intent intent=new Intent(FindPwd.this,LoginActivity.class);
					startActivity(intent);
					
					//结束该Activity
					finish();
					overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
				}
			}			
		});
        
        //为返回主页键绑定事件监听器
        tvReturn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent=new Intent(FindPwd.this,LoginActivity.class);
				startActivity(intent);
				
				finish();
				overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			}
		});
   	}
	
	//信息校验
	private boolean validate() {
		// TODO Auto-generated method stub
		String username=etFindName.getText().toString().trim();
		if(username.equals(""))
		{
			DialogUtil.showDialog(this, "用户手机号是必填项！", false);
			
			GetFoucus(etFindName);
			return false;
		}
		
		if(!isNumeric(username))
		{
			DialogUtil.showDialog(this, "请输入有效手机号！", false);
			etFindName.setText("");
			return false;
		}
		
		
		String pwd1=etFindPass.getText().toString().trim();
		if(pwd1.equals(""))
		{
			DialogUtil.showDialog(this, "新口令设置 是必填项！", false);
			
			GetFoucus(etFindPass);
			return false;
		}
		
		String pwd2=etFindConfirmPass.getText().toString().trim();
		if(pwd2.equals(""))
		{
			DialogUtil.showDialog(this, "口令确认是必填项！", false);
			
			GetFoucus(etFindConfirmPass);
			return false;
		}
		
		if(!pwd1.equals(pwd2))
		{
			DialogUtil.showDialog(this, "确认密码错误，请重新输入！", false);
			etFindConfirmPass.setText("");
			return false;
		}
		
		String code=etFindConfirmNum.getText().toString().trim();
		if(code.equals(""))
		{
			DialogUtil.showDialog(this, "手机验证码是必填项！", false);
			GetFoucus(etFindConfirmNum);
			return false;
		}
		
		return true;
	}
	
	public static boolean isNumeric(String str)
	{
		for (int i = str.length();--i>=0;)
		{
			if (!Character.isDigit(str.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}
	
	//使编辑框重获焦点
	public void GetFoucus(EditText et)
	{
		et.setFocusable(true);
		et.setFocusableInTouchMode(true);
		et.requestFocus();
		et.findFocus();
	}
}
