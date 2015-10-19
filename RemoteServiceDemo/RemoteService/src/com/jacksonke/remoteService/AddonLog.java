package com.jacksonke.remoteService;

import android.util.Log;

public class AddonLog {
	private final static String TAG = "AddonTag";
	final static boolean enableLog = true;
	
	public static void log(String logString){
		if (enableLog){
			Log.i(TAG, "[addon]" + logString);
		}
	}
	
	public static void error(String logString){
		if (enableLog){
			Log.e(TAG, "[addon]" + logString);
		}
	}
}
