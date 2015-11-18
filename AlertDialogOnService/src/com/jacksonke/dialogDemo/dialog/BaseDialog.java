package com.jacksonke.dialogDemo.dialog;

import java.lang.ref.WeakReference;

import android.app.AlertDialog;
import android.content.Context;

public abstract class BaseDialog {
	protected AlertDialog mDialog;
	protected Context mContext;
	
	public static final String DIALOG_STEP_ONE ="stepOne";
	public static final String DIALOG_STEP_TWO ="stepTwo";
	
	static WeakReference<BaseDialog> sDialogHolder;
	
	protected BaseDialog(Context context){
		mContext = context;
	}
	
	abstract protected void showDialog();
	
	public void show(){
		BaseDialog preDialog = null;
		AlertDialog showingDialog = null;
		if (sDialogHolder != null){
			preDialog = sDialogHolder.get();
		}
		
		if (preDialog != null && preDialog.mDialog.isShowing()){
			showingDialog = preDialog.mDialog;
		}
		
		showDialog();
		
		sDialogHolder = new WeakReference<BaseDialog>(this);
		
		if (showingDialog != null){
			showingDialog.dismiss();
		}
		
	}
	
	public static BaseDialog newInstance(Context context, String name){
		if (DIALOG_STEP_ONE.equals(name)){
			return new StepOneDialog(context);
		}
		
		if (DIALOG_STEP_TWO.equals(name)){
			return new StepTwoDialog(context);
		}
		
		return null;
	}
	
	
}
