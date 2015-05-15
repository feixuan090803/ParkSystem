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

public class ToAboutUs extends Activity 
{

	TextView tvReturn;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.toaboutus);
                
        //°ó¶¨·µ»Ø¼ü
        tvReturn=(TextView)findViewById(R.id.title_return);
 
        tvReturn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
	
}

