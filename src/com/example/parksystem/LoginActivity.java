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
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.R.anim;

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
        
        //��ȡ�����������༭��
        etName=(EditText)findViewById(R.id.username_edit);
        etPass=(EditText)findViewById(R.id.password_edit);
        
        //��ȡע��ѡ����
        txReg=(TextView)findViewById(R.id.register_link);
        
        //��ȡ�һ�����ѡ����
        txFindPwd=(TextView)findViewById(R.id.find_pwd);
        
        //��ȡ�����е�¼��ť
        bnLogin=(Button)findViewById(R.id.signin_button);
        
        //Ϊ��¼��ť���¼�������
        bnLogin.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(validate())
				{
					if(loginPro())
					{
						//����¼�ɹ�������main activity
						Intent intent=new Intent(LoginActivity.this,Main.class);
						startActivity(intent);
						
						//������Activity
						finish();
						         
					}
					else {
						DialogUtil.showDialog(LoginActivity.this,"�û�����������������������룡",false);	
					}
				}
			}			
		});
    
        //Ϊע��ѡ����¼�������
        txReg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				// ת��ע�ᴰ��
				Intent intent=new Intent(LoginActivity.this,Register.class);
				startActivity(intent);
				
				//������Activity
				finish();
				
				//���������л�Ч��
				overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right); 
			}
		});
        
        //Ϊ�һ�����ѡ������¼�������
        txFindPwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ת���һ����봰��
				// ת��ע�ᴰ��
				Intent intent=new Intent(LoginActivity.this,FindPwd.class);
				startActivity(intent);
				
				//������Activity
				finish();
				overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right); 
			}
		});
    }
    
  //��ϢУ��
	private boolean validate() {
		// TODO Auto-generated method stub
		String username=etName.getText().toString().trim();
		if(username.equals(""))
		{
			DialogUtil.showDialog(this, "�û��˻��Ǳ����", false);
			return false;
			
		}
		String pwd=etPass.getText().toString().trim();
		if(pwd.equals(""))
		{
			DialogUtil.showDialog(this, "�û������Ǳ����", false);
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
			DialogUtil.showDialog(this,"��������Ӧ�쳣�����Ժ�����!",false);
			e.printStackTrace();
		}
		return false;
	}
	
	private JSONObject query(String username,String password)
		throws Exception
	{
		//ʹ��Map��װ�������
		Map<String, String> map=new HashMap<String, String>();
		map.put("user", username);
		map.put("pass", password);
		
		//���巢�������URL
		String url=HttpUtil.BASE_URL+"login.jsp";
		
		//��������
		return new JSONObject(HttpUtil.postRequest(url,map));
	}
	
}