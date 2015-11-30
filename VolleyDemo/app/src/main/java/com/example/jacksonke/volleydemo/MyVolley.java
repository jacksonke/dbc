package com.example.jacksonke.volleydemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2015/11/25.
 */
public class MyVolley {
    private static MyVolley mInstance;
    private static Context mCtx;

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;


    public static synchronized MyVolley getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyVolley(context);
        }
        return mInstance;
    }

    private MyVolley(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {

                        if (url.contains("file://")) {
                            String tmppath = url.substring(url.indexOf("file://") + 7);
                            Bitmap bitmap = BitmapFactory.decodeFile(url.substring(url.indexOf("file://") + 7));

                            Debug.d(this, "222222222222");

                            // TODO 处理图片尺寸

                            if (bitmap != null){
                                putBitmap(url, bitmap);
                            }

                            // 如果本地图片不存在呢？返回是null，会走volley网络请求的流程,这样是否有其他问题
                            // 加载图片失败，会使用默认的失败图片


                            return bitmap;
                        }
                        else {
                            return cache.get(url);
                        }

                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

}
