package com.cseunited.alumni.cseunited;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


/**
 * Created by Debanik on 02-11-2017.
 */

public class VolleyChannel {
    private static VolleyChannel mInstance;
    private ImageLoader mLoader;
    private RequestQueue mQueue;
    private static Context mContext;

    private VolleyChannel(Context context){
        mContext = context;
        mQueue = getRequestQueue();
        mLoader = new ImageLoader(getRequestQueue(), new ImageLoader.ImageCache() {
            private LruCache<String, Bitmap> cache = new LruCache<>(30);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }
    public static synchronized VolleyChannel getInstance(Context context){
        if(mInstance==null)
            mInstance = new VolleyChannel(context);
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mQueue==null)
            mQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        return mQueue;
    }

    public void addToRequestQueue(Request request, Object tag){
        request.setTag(tag);
        getRequestQueue().add(request);
    }

    public void cancelRequests(Object tag){
        getRequestQueue().cancelAll(tag);
    }

    public ImageLoader getImageLoader(){return mLoader;}
}
