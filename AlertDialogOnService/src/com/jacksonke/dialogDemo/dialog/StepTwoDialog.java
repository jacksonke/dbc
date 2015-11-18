package com.jacksonke.dialogDemo.dialog;


import com.jacksonke.alertdialogonservice.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.view.WindowManager;

public class StepTwoDialog extends BaseDialog{
	

	public StepTwoDialog(Context context) {
		super(context);
	}

	@Override
	protected void showDialog() {
		Resources rs = mContext.getResources();
		String message = rs.getString(R.string.step_two_msg_body);
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		mDialog = builder
		.setTitle(rs.getString(R.string.title_step_two))
		.setMessage(message)
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
