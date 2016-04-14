package com.example.jacksonke.scriptdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScriptDemoMainActivity extends AppCompatActivity {

    @Bind(R.id.btn_ndk)
    Button btnNdk;
    @Bind(R.id.btn_aidl)
    Button btnAidl;
    @Bind(R.id.btn_assets)
    Button btnAssets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_script_demo_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mConnetion, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnetion);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_script_demo_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    ISpeak mSpeak;
    boolean mHasBound = false;


    private ServiceConnection mConnetion = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mSpeak = ISpeak.Stub.asInterface(service);
            mHasBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mSpeak = null;
            mHasBound = false;
        }
    };

    @OnClick({R.id.btn_ndk, R.id.btn_aidl, R.id.btn_assets})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ndk:
                Toast.makeText(this, new HelloNdk().stringFromJNI(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_aidl:
                // sss
                if (mSpeak != null) {
                    try {
                        mSpeak.speak("Hey, pretty!");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_assets:
                try {
                    String[] paths = getAssets().list("");
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String path : paths) {
                        stringBuilder.append(path).append('\n');
                    }

                    Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

}
