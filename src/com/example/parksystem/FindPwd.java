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
        
        //���ֻ���֤��ť
        btnConfirmNum=(Button)findViewById(R.id.find_confirm_num);
        
        //��ȷ���޸İ�ť
        btnConfirmReg=(Button)findViewById(R.id.find_confirm_register);
        //btnConfirmReg.setEnabled(false); //��ʼ��Ϊ������
        
        
        //�� Ҫ�޸�������ֻ��� �༭��
        etFindName=(EditText)findViewById(R.id.find_username_edit);
        
        //�� �ֻ���֤�� �༭��
        etFindConfirmNum=(EditText)findViewById(R.id.find_numconfirm_edit);
        
        //������������༭��
        etFindPass=(EditText)findViewById(R.id.find_password_edit);
        
        //��������֤�༭��
        etFindConfirmPass=(EditText)findViewById(R.id.find_pwdconfirm_edit);
        
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
					overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
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
				overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			}
		});
   	}
	
	//��ϢУ��
	private boolean validate() {
		// TODO Auto-generated method stub
		String username=etFindName.getText().toString().trim();
		if(username.equals(""))
		{
			DialogUtil.showDialog(this, "�û��ֻ����Ǳ����", false);
			
			GetFoucus(etFindName);
			return false;
		}
		
		if(!isNumeric(username))
		{
			DialogUtil.showDialog(this, "��������Ч�ֻ��ţ�", false);
			etFindName.setText("");
			return false;
		}
		
		
		String pwd1=etFindPass.getText().toString().trim();
		if(pwd1.equals(""))
		{
			DialogUtil.showDialog(this, "�¿������� �Ǳ����", false);
			
			GetFoucus(etFindPass);
			return false;
		}
		
		String pwd2=etFindConfirmPass.getText().toString().trim();
		if(pwd2.equals(""))
		{
			DialogUtil.showDialog(this, "����ȷ���Ǳ����", false);
			
			GetFoucus(etFindConfirmPass);
			return false;
		}
		
		if(!pwd1.equals(pwd2))
		{
			DialogUtil.showDialog(this, "ȷ������������������룡", false);
			etFindConfirmPass.setText("");
			return false;
		}
		
		String code=etFindConfirmNum.getText().toString().trim();
		if(code.equals(""))
		{
			DialogUtil.showDialog(this, "�ֻ���֤���Ǳ����", false);
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
	
	//ʹ�༭���ػ񽹵�
	public void GetFoucus(EditText et)
	{
		et.setFocusable(true);
		et.setFocusableInTouchMode(true);
		et.requestFocus();
		et.findFocus();
	}
}
