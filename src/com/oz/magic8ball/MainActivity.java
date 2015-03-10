package com.oz.magic8ball;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends Activity implements SensorEventListener {
	String words[];
	Button show;
	// ToggleButton hold;
	private SensorManager sensorManager;
	private long lastUpdate;
	View view;
	private Animation animation1;
	FrameLayout ball;
	ImageView magicball;
	MediaPlayer mp;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		words = getResources().getStringArray(R.array.words);
		show = (Button) findViewById(R.id.show);
		ball = (FrameLayout) findViewById(R.id.ball);
		magicball = (ImageView) findViewById(R.id.magicball);
		mp = MediaPlayer.create(getApplicationContext(), R.raw.sound);
	    
		show.setText("Shake");
		view = show;

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		lastUpdate = System.currentTimeMillis();
		
		
		show.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onshake();
			}
		});
	}

	private void getAccelerometer(SensorEvent event) {
		float[] values = event.values;
		// Movement
		float x = values[0];
		float y = values[1];
		float z = values[2];
		// get acceleration
		float accelationSquareRoot = (x * x + y * y + z * z)
				/ (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
		// get current time
		long actualTime = System.currentTimeMillis();
		if (accelationSquareRoot >= 2) {
			if (actualTime - lastUpdate < 200) {
				return;
			}
			lastUpdate = actualTime;
			// if(hold.getText().equals("HOLD")){
			// on shake
			onshake();
			

		}
	}

	private void onshake() {
		// TODO Auto-generated method stub
		if(SharedPreferencesHelper.getSharedPreferences(getApplicationContext(), "sound", true)){
		mp.start();
		}
		animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.shake);
		ball.clearAnimation();
		ball.setAnimation(animation1);
		ball.startAnimation(animation1);
		if(SharedPreferencesHelper.getSharedPreferences(getApplicationContext(), "vibrate", true)){
		Vibrator v = (Vibrator) this
				.getSystemService(Context.VIBRATOR_SERVICE);
		// Vibrate for 500 milliseconds
		v.vibrate(500);
		}
		animate();
		Random rn = new Random();
		int answer = rn.nextInt();
		//show.setRotation(answer % 360);
		int a = answer % 19;
		if (a < 19 && a >= 0) {
			show.setText(words[a]);
		}
		
	}

	private void animate() {

		// TODO Auto-generated method stub

		final Animation animation = AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in);
		animation.setDuration(3000);
		view.startAnimation(animation);

	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			getAccelerometer(event);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		// register this class as a listener for the orientation and
		// accelerometer sensors
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		// unregister listener
		super.onPause();
		sensorManager.unregisterListener(this);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
	        case R.id.menu_add:
	        	Intent intent = new Intent();
				intent.setClass(getApplicationContext(), Setting.class);
				startActivity(intent);
				 break;
                
	        default:
				break;
				
		}
		return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.add_menu_option, menu);
    	return super.onCreateOptionsMenu(menu);
	}
}
