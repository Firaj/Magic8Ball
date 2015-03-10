package com.oz.magic8ball;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Setting extends Activity {
	static CheckBox s,v;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
	
		s=(CheckBox) findViewById(R.id.s);
		v=(CheckBox) findViewById(R.id.v);
		
		s.setChecked(SharedPreferencesHelper.getSharedPreferences(getApplicationContext(), "sound", true));
		v.setChecked(SharedPreferencesHelper.getSharedPreferences(getApplicationContext(), "vibrate", true));
		
		s.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(s.isChecked()){
					Boolean TF = SharedPreferencesHelper.setSharedPreferences(getApplicationContext(), "sound", true);
					}else{
						Boolean TF = SharedPreferencesHelper.setSharedPreferences(getApplicationContext(), "sound",false);
							
				}
				
			}
		});
		
v.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(v.isChecked()){
					Boolean TF = SharedPreferencesHelper.setSharedPreferences(getApplicationContext(), "vibrate", true);
					}else{
						Boolean TF = SharedPreferencesHelper.setSharedPreferences(getApplicationContext(), "vibrate", false);
							
				}
				
			}
		});
		
	}
}
