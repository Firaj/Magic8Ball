package com.oz.magic8ball;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
	
	private static String PREFS_NAME ="setting";
	public static Boolean getSharedPreferences(Context context, String key, Boolean defaultValue){
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		Boolean prefsValues = settings.getBoolean(key, defaultValue);
		return prefsValues;
	}
	
	public static boolean setSharedPreferences(Context context, String key, Boolean value) {
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		
		try{
			SharedPreferences.Editor editor = settings.edit();
			editor.putBoolean(key, value);
			editor.commit();
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
