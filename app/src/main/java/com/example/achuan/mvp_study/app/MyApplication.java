package com.example.achuan.mvp_study.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by achuan on 16-9-21.
 */
public class MyApplication extends Application {
    public static RequestQueue sQueues;//声明请求队列
    @Override
    public void onCreate() {
        super.onCreate();
        //创建队列对象
        sQueues= Volley.newRequestQueue(getApplicationContext());
    }
    //让全局得到这个请求队列
    public static RequestQueue getQueues() {
        return sQueues;
    }
}
