package com.jacksonke.dialogDemo;

import com.jacksonke.dialogDemo.event.*;
import com.jacksonke.dialogDemo.utils.Debug;

import de.greenrobot.event.EventBus;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MyService extends Service{
	
	/** Command to the service to display a message */
    static final int MSG_DRIVE = 1;
    
    DialogHelper dialogHelper;

    /**
     * Handler of incoming messages from clients.
     */
    static class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_DRIVE:
//                	Handler handler = new Handler();
        	    	/*handler.*/postDelayed(new Runnable() {
        				@Override
        				public void run() {
        					EventBus.getDefault().post(new EventMsgShowStepOneDialog(null));
        				}
        			}, 100);
        	    	
        	    	/*handler.*/postDelayed(new Runnable() {
        				@Override
        				public void run() {
        					EventBus.getDefault().post(new EventMsgShowStepTwoDialog(null));
        				}
        			}, 3000);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    final Messenger mMessenger = new Messenger(new IncomingHandler());


	@Override
	public IBinder onBind(Intent intent) {
		Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT).show();
		return mMessenger.getBinder();
	}


	@Override
	public void onCreate() {
		Debug.d(this, "onCreate");
		super.onCreate();
		
		EventBus.getDefault().register(this);
		dialogHelper = new DialogHelper(this);
	}

	@Override
	public void onDestroy() {
		Debug.d(this, "onDestroy");
		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}
	
	public void onEventMainThread(EventMsgShowStepOneDialog event){
		Debug.d(this, "recv event EventMsgShowStepOneDialog");
		dialogHelper.showStepOneDialog();
	}

	public void onEventMainThread(EventMsgShowStepTwoDialog event){
		Debug.d(this, "recv event EventMsgShowStepTwoDialog");
		dialogHelper.showStepTwoDialog();
	}
}
