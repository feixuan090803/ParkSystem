package com.example.parksystem;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;


import com.example.parksystem.util.HttpUtil;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//Download by http://www.codefans.net
public class LoginActivity extends Activity {
	
	EditText etName,etPass;
	Button bnLogin;
	TextView txReg,txFindPwd;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        
        //获取界面中两个编辑框
        etName=(EditText)findViewById(R.id.username_edit);
        etPass=(EditText)findViewById(R.id.password_edit);
        
        //获取注册选择项
        txReg=(TextView)findViewById(R.id.register_link);
        
        //获取找回密码选择项
        txFindPwd=(TextView)findViewById(R.id.find_pwd);
        
        //获取界面中登录按钮
        bnLogin=(Button)findViewById(R.id.signin_button);
        
        //为登录按钮绑定事件监听器
        bnLogin.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(validate())
				{
					if(loginPro())
					{
						//若登录成功，启动main activity
						Intent intent=new Intent(LoginActivity.this,Main.class);
						startActivity(intent);
						
						//结束该Activity
						finish();
					}
					else {
						DialogUtil.showDialog(LoginActivity.this,"用户名或者密码错误，请重新输入！",false);	
					}
				}
			}			
		});
    
        //为注册选项绑定事件监听器
        txReg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				// 转至注册窗口
				Intent intent=new Intent(LoginActivity.this,Register.class);
				startActivity(intent);
				
				//结束该Activity
				finish();
				
			}
		});
        
        //为找回密码选择项绑定事件监听器
        txFindPwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 转至找回密码窗口
				// 转至注册窗口
				Intent intent=new Intent(LoginActivity.this,FindPwd.class);
				startActivity(intent);
				
				//结束该Activity
				finish();
			}
		});
    }
    
  //信息校验
	private boolean validate() {
		// TODO Auto-generated method stub
		String username=etName.getText().toString().trim();
		if(username.equals(""))
		{
			DialogUtil.showDialog(this, "用户账户是必填项！", false);
			return false;
			
		}
		String pwd=etPass.getText().toString().trim();
		if(pwd.equals(""))
		{
			DialogUtil.showDialog(this, "用户口令是必填项！", false);
			return false;
		}
		return true;
	}

	private boolean loginPro() {
		// TODO Auto-generated method stub
		String username=etName.getText().toString();
		String pwd=etPass.getText().toString();
		
		JSONObject jsonObject;
		try {
			//jsonObject=query(username,pwd);
			//if(jsonObject.getInt("userID">0))
			{
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			DialogUtil.showDialog(this,"服务器相应异常，请稍后再试!",false);
			e.printStackTrace();
		}
		return false;
	}
	
	private JSONObject query(String username,String password)
		throws Exception
	{
		//使用Map封装请求参数
		Map<String, String> map=new HashMap<String, String>();
		map.put("user", username);
		map.put("pass", password);
		
		//定义发送请求的URL
		String url=HttpUtil.BASE_URL+"login.jsp";
		
		//发送请求
		return new JSONObject(HttpUtil.postRequest(url,map));
	}
	
}