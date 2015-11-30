package com.example.jacksonke.volleydemo;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.io.File;


public class ImageListActivity extends  ActionBarActivity {

    ListView mListView;

    ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        mListView = (ListView)findViewById(R.id.listView);

        mListView.setAdapter(new MyListAdapter(this));
        mImageLoader = MyVolley.getInstance(this).getImageLoader();


        testLocalFile();
    }

    void testLocalFile(){
//        Debug.d(this, Environment.getExternalStorageDirectory().getPath());


        String localPath = this.getFilesDir().getAbsolutePath();
        Debug.d(this, localPath);

//        String myUri = Uri.fromFile(new File(localPath)).toString();
//        Toast.makeText(this, myUri, Toast.LENGTH_SHORT).show();
//        Debug.d(this, myUri);
    }

    static String[] urls = {
            "file:///data/data/com.example.jacksonke.volleydemo/files/1.png",
            "http://img5.imgtn.bdimg.com/it/u=2322491703,1397411048&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=891874148,2905816277&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=1176354637,957768932&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=912168961,730829377&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=4048609474,84503687&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1973278526,3947629936&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1718502807,3887011184&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1236938298,2806856762&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3208791881,1923071650&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1138875461,2330391495&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3015429600,3999841203&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=2821096243,3065462094&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=4003722060,122592249&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1710548267,3315446377&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2815910831,19269169&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=4428749,2565181497&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=102905829,88289719&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3534067960,2527327757&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2524795027,3898684503&fm=21&gp=0.jpg",
    };

    class MyListAdapter extends BaseAdapter{

        Context mContext;
        private LayoutInflater myInflater;

        class ViewHolder{
            public NetworkImageView mImageView;
        }

        public MyListAdapter(Context context){
            mContext = context;
            myInflater = LayoutInflater.from(mContext);
        }
        @Override
        public int getCount() {
            return urls.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        // 使用NetworkImageView
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null){
                convertView = myInflater.inflate(R.layout.layout_my_list_cell, null);
                viewHolder = new ViewHolder();
                viewHolder.mImageView = (NetworkImageView)convertView.findViewById(R.id.imageView);
                viewHolder.mImageView.setDefaultImageResId(R.drawable.ic_launcher);
                viewHolder.mImageView.setErrorImageResId(R.drawable.ic_err);
                convertView.setTag(viewHolder);
            }
            else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.mImageView.setImageUrl(urls[(int)getItemId(position)], mImageLoader);

            return convertView;
        }

        // 使用ImageView
        /*@Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null){
                convertView = myInflater.inflate(R.layout.layout_my_list_cell, null);
                viewHolder = new ViewHolder();
                viewHolder.mImageView = (NetworkImageView)convertView.findViewById(R.id.imageView);
                convertView.setTag(viewHolder);
            }
            else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            mImageLoader.get(urls[(int)getItemId(position)], ImageLoader.getImageListener(viewHolder.mImageView,
                    R.drawable.ic_launcher, R.drawable.ic_err));

            return convertView;
        }*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_list, menu);
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
}
