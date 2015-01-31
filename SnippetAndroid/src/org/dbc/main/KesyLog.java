package org.dbc.main;

import android.util.Log;

public class KesyLog {
	static final String TAG = "kesy";
	static boolean DEBUG = true;
	public static void log(String logString){
		if (DEBUG){
			Log.i(TAG, "" + logString);
		}
	}
}
