package com.example.parksystem;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends Activity
{
	Button btnConfirmNum,btnConfirmReg;
	EditText etRegName,etRegPass,etRegConfirmPass,etRegConfirmNum;
	TextView tvReturn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.toregister);
        
        //���ֻ���֤��ť
        btnConfirmNum=(Button)findViewById(R.id.receive_confirm_num);
        
        //��ȷ��ע�ᰴť
        btnConfirmReg=(Button)findViewById(R.id.confirm_register);
        //btnConfirmReg.setEnabled(false); //��ʼ��Ϊ������
        
        //��ע���ֻ��ű༭��
        etRegName=(EditText)findViewById(R.id.reg_username_edit);
        
        //��ע������༭��
        etRegPass=(EditText)findViewById(R.id.reg_password_edit);
        
        //��������֤�༭��
        etRegConfirmPass=(EditText)findViewById(R.id.reg_pwdconfirm_edit);
        
        //���ֻ���֤��༭��
        etRegConfirmNum=(EditText)findViewById(R.id.reg_numconfirm_edit);
        
        //�󶨷��ص�½���水ť
        tvReturn=(TextView)findViewById(R.id.title_return);
        
        //Ϊȷ��ע�ᰴť���¼�������
        btnConfirmReg.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(validate())
				{
					DialogUtil.showDialog(Register.this,"ע��ɹ�,�����µ�¼��",false);	
					//��ע��ɹ������ص�¼����
					Intent intent=new Intent(Register.this,LoginActivity.class);
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
				// TODO Auto-generated method stub
				Intent intent=new Intent(Register.this,LoginActivity.class);
				startActivity(intent);
				
				finish();
				overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			}
		});
   	}
	
	//��ϢУ��
	private boolean validate() {
		// TODO Auto-generated method stub
		String username=etRegName.getText().toString().trim();
		if(username.equals(""))
		{
			DialogUtil.showDialog(this, "ע���˻��Ǳ����", false);
			
			//��ȡ����
			GetFoucus(etRegName);
			
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
			DialogUtil.showDialog(this, "�û������Ǳ����", false);
			
			GetFoucus(etRegPass);
			
			return false;
		}
		
		String pwd2=etRegConfirmPass.getText().toString().trim();
		if(pwd2.equals(""))
		{
			DialogUtil.showDialog(this, "ȷ�Ͽ����Ǳ����", false);
			
			GetFoucus(etRegConfirmPass);
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
			
			GetFoucus(etRegConfirmNum);
			return false;
		}
		
		return true;
	}
	
	//�ж��ַ����Ƿ�ȫΪ����
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
