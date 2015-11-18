package com.jacksonke.dialogDemo.dialog;


import com.jacksonke.alertdialogonservice.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.WindowManager;

public class StepOneDialog extends BaseDialog{
	
	static final String ColorString = "One";

	public StepOneDialog(Context context) {
		super(context);
	}

	@Override
	protected void showDialog() {
		Resources rs = mContext.getResources();
		String message = rs.getString(R.string.step_one_msg_body);
		SpannableString ss = new SpannableString(message);
		int index = message.indexOf(ColorString);
		ss.setSpan(new ForegroundColorSpan(Color.RED), index, index+ColorString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		mDialog = builder
		.setTitle(rs.getString(R.string.title_step_one))
		.setMessage(ss)
		.setPositiveButton(rs.getString(R.string.yes_text), new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		})
		.setNegativeButton(rs.getString(R.string.no_text), new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		})
		.create();
		
		mDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		mDialog.show();
		
	}

}
