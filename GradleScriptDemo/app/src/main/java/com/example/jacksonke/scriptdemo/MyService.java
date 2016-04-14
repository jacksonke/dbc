package com.example.jacksonke.scriptdemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private static class MyBinder extends ISpeak.Stub{
        Context mContext;

        public MyBinder(MyService context){
            super();
            mContext = context;
        }

        @Override
        public void speak(String word) throws RemoteException {
            Toast.makeText(mContext, "[aidl server]:  -_- " + word, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBinder(this);
    }
}
