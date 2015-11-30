package com.example.jacksonke.volleydemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class VolleyDemoActivity extends ActionBarActivity implements View.OnClickListener{

    RequestQueue mQueue;

    Button stringRequestButton;
    Button imageRequestButton;
    Button startListActivityButton;
    ImageView mImageView;

    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_demo);
        stringRequestButton = (Button)findViewById(R.id.button_string_request);
        stringRequestButton.setOnClickListener(this);

        imageRequestButton = (Button)findViewById(R.id.button_img_request);
        imageRequestButton.setOnClickListener(this);

        startListActivityButton = (Button)findViewById(R.id.button_start_list_activity);
        startListActivityButton.setOnClickListener(this);


        mImageView = (ImageView)findViewById(R.id.imageView_request);

        mQueue = Volley.newRequestQueue(this);

        stringRequest= new StringRequest("http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Debug.d(this, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Debug.d(this, error.toString());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_volley_demo, menu);
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

    void test(){
        mQueue.add(stringRequest);
    }

//    final static String IMAGE_URL = "http://img2.imgtn.bdimg.com/it/u=652820028,1048888718&fm=21&gp=0.jpg";

    final static String IMAGE_URL = "file:///data/data/com.example.jacksonke.volleydemo/files/1.png";

    void updateImageView(){
        ImageRequest request = new ImageRequest(IMAGE_URL,
                new Response.Listener<Bitmap>(){
                    @Override
                    public void onResponse(Bitmap response) {
                        mImageView.setImageBitmap(response);
                    }
                },
                0, 0, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Debug.d(this, error.toString());
                    }
                });

        MyVolley.getInstance(this).addToRequestQueue(request);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_string_request){
            test();
        }
        else if (id == R.id.button_img_request){
            updateImageView();
        }
        else if (id == R.id.button_start_list_activity){
            Intent intent = new Intent(this, ImageListActivity.class);
            startActivity(intent);
        }
    }
}
