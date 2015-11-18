package com.jacksonke.dialogDemo;


import com.jacksonke.alertdialogonservice.R;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
		
		Button mDriveButton;
		
		/** Messenger for communicating with the service. */
	    Messenger mService = null;

	    /** Flag indicating whether we have called bind on the service. */
	    boolean mBound;

	    /**
	     * Class for interacting with the main interface of the service.
	     */
	    private ServiceConnection mConnection = new ServiceConnection() {
	        public void onServiceConnected(ComponentName className, IBinder service) {
	            // This is called when the connection with the service has been
	            // established, giving us the object we can use to
	            // interact with the service.  We are communicating with the
	            // service using a Messenger, so here we get a client-side
	            // representation of that from the raw IBinder object.
	            mService = new Messenger(service);
	            mBound = true;
	        }

	        public void onServiceDisconnected(ComponentName className) {
	            // This is called when the connection with the service has been
	            // unexpectedly disconnected -- that is, its process crashed.
	            mService = null;
	            mBound = false;
	        }
	    };

	    public void startDrive() {
	        if (!mBound){ 
	        	return;
	        }
	        
	        // Create and send a message to the service, using a supported 'what' value
	        Message msg = Message.obtain(null, MyService.MSG_DRIVE, 0, 0);
	        try {
	            mService.send(msg);
	        } catch (RemoteException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    

		@Override
		public void onStart() {
			super.onStart();
			
			getActivity().bindService(new Intent(getActivity(), MyService.class), mConnection,
		            Context.BIND_AUTO_CREATE);
		}

		@Override
		public void onStop() {
			if (mBound) {
	            getActivity().unbindService(mConnection);
	            mBound = false;
	        }
			
			super.onStop();
		}

		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
			
			mDriveButton = (Button)view.findViewById(R.id.button_drive); 
			
			mDriveButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					startDrive();
				}
			});
			super.onViewCreated(view, savedInstanceState);
		}
	}

}
