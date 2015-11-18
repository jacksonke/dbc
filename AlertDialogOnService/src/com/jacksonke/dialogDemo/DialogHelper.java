package com.jacksonke.dialogDemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.jacksonke.dialogDemo.dialog.BaseDialog;

import android.content.Context;

public class DialogHelper {
	
	private Context mContext;
	
	public DialogHelper(Context context) {
		this.mContext = context;
	}
	
	public void showStepOneDialog(){
		BaseDialog dialog = BaseDialog.newInstance(mContext, BaseDialog.DIALOG_STEP_ONE);
		if (dialog != null){
			dialog.show();
		}
	}
	
	public void showStepTwoDialog(){
		BaseDialog dialog = BaseDialog.newInstance(mContext, BaseDialog.DIALOG_STEP_TWO);
		if (dialog != null){
			dialog.show();
		}
	}
	
	/*public void collapseStatusBarPanels(){
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		Object service = mContext.getSystemService("statusbar");
		Class<?> statusbarManager;
		try {
			statusbarManager = Class.forName("android.app.StatusBarManager");
			Method collapse = null;
			if(currentapiVersion <= 16){
			    collapse = statusbarManager.getMethod("collapse");
			}else{
			    collapse = statusbarManager.getMethod("collapsePanels");
			}
			collapse.setAccessible(true);
			collapse.invoke(service);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}*/
}
