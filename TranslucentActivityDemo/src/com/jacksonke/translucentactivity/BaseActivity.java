package com.jacksonke.translucentactivity;

import java.lang.reflect.Field;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.WindowManager;

public class BaseActivity extends ActionBarActivity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT){
			initActionBar();
		}
	}
	
	@TargetApi(Build.VERSION_CODES.KITKAT)
	private void initActionBar(){
		// 这里不会
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		getWindow().setBackgroundDrawableResource(R.color.base_background_color);
		
		getSupportActionBar().setBackgroundDrawable(this.getResources().getDrawable(android.R.color.transparent));
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		
		
		ViewGroup vg = (ViewGroup)(getWindow().getDecorView());
		vg.findViewById(android.R.id.content).setPadding(0, getActionBarHeight()+getStatusBarHeight(), 0, 0);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	// 获取手机状态栏高度
    public int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }
	
    // 获取ActionBar的高度
    public int getActionBarHeight() {
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        // 如果资源是存在的、有效的
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)){
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }
}
