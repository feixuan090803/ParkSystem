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
	EditText etRegName,etRegPass,etRegConfirmPass,etRegConfirmNum;
	TextView tvReturn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.findpwd);
        
        //���ֻ���֤��ť
        btnConfirmNum=(Button)findViewById(R.id.find_confirm_num);
        
        //��ȷ���޸İ�ť
        btnConfirmReg=(Button)findViewById(R.id.find_confirm_register);
        //btnConfirmReg.setEnabled(false); //��ʼ��Ϊ������
        
        
        //�� Ҫ�޸�������ֻ��� �༭��
        etRegName=(EditText)findViewById(R.id.find_username_edit);
        
        //�� �ֻ���֤�� �༭��
        etRegConfirmNum=(EditText)findViewById(R.id.find_numconfirm_edit);
        
        //������������༭��
        etRegPass=(EditText)findViewById(R.id.find_password_edit);
        
        //��������֤�༭��
        etRegConfirmPass=(EditText)findViewById(R.id.find_pwdconfirm_edit);
        
        //�󶨷��ص�½���水ť
        tvReturn=(TextView)findViewById(R.id.find_title_return);
        
        //Ϊȷ���޸İ�ť���¼�������
        btnConfirmReg.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(validate())
				{
					DialogUtil.showDialog(FindPwd.this,"�޸ĳɹ�,�����µ�¼��",false);	
					//��ע��ɹ������ص�¼����
					Intent intent=new Intent(FindPwd.this,LoginActivity.class);
					startActivity(intent);
					
					//������Activity
					finish();
				}
			}			
		});
        
        //Ϊ������ҳ�����¼�������
        tvReturn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent=new Intent(FindPwd.this,LoginActivity.class);
				startActivity(intent);
				
				finish();
			}
		});
   	}
	
	//��ϢУ��
	private boolean validate() {
		// TODO Auto-generated method stub
		String username=etRegName.getText().toString().trim();
		if(username.equals(""))
		{
			DialogUtil.showDialog(this, "�û��ֻ����Ǳ����", false);
			return false;
		}
		
		if(!isNumeric(username))
		{
			DialogUtil.showDialog(this, "��������Ч�ֻ��ţ�", false);
			etRegName.setText("");
			return false;
		}
		
		
		String pwd1=etRegPass.getText().toString().trim();
		if(pwd1.equals(""))
		{
			DialogUtil.showDialog(this, "�¿������� �Ǳ����", false);
			return false;
		}
		
		String pwd2=etRegConfirmPass.getText().toString().trim();
		if(pwd2.equals(""))
		{
			DialogUtil.showDialog(this, "����ȷ���Ǳ����", false);
			return false;
		}
		
		if(!pwd1.equals(pwd2))
		{
			DialogUtil.showDialog(this, "ȷ������������������룡", false);
			etRegConfirmPass.setText("");
			return false;
		}
		
		String code=etRegConfirmNum.getText().toString().trim();
		if(code.equals(""))
		{
			DialogUtil.showDialog(this, "�ֻ���֤���Ǳ����", false);
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
}
