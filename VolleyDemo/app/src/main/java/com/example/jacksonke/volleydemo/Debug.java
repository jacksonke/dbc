package com.example.jacksonke.volleydemo;

import com.example.jacksonke.volleydemo.BuildConfig;

import android.util.Log;

public class Debug {
	static boolean isDebug = BuildConfig.DEBUG;
	static final String TAG = "volleyDemo";

	public static int d(Object c, String msg) {
        return Log.d(getTag(c), msg);
    }

    public static int e(Object c, String msg) {
        return Log.e(getTag(c), msg);
    }

    public static int i(Object c, String msg) {
        return Log.i(getTag(c), msg);
    }

    public static int v(Object c, String msg) {
        return Log.v(getTag(c), msg);
    }

    public static int w(Object c, String msg) {
        return Log.w(getTag(c), msg);
    }

    public static int wtf(Object c, String msg) {
        return Log.wtf(getTag(c), msg);
    }
	
	public static String getTag(Object c) {
        return TAG + "" + c.getClass().getName();
    }
}
