package com.example.achuan.mvp_study.model.http;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.achuan.mvp_study.app.MyApplication;

import java.util.Map;

/**
 * Created by achuan on 16-9-21.
 */
public class VolleyRequestUtil {
    //１－封装get请求(类方法,通过“类名.方法名”就可以调用该方法)
    public static void RequestGet(String url,String tag,final VolleyCallbackListener listener)
    {
        MyApplication.getQueues().cancelAll(tag);//取消之前的请求，防止重复请求
        StringRequest get_request=new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        listener.onFinish(s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        listener.onError(volleyError);
                    }
                }
        );
        get_request.setTag(tag);//为该请求设置一个tag标志
        MyApplication.getQueues().add(get_request);
        MyApplication.getQueues().start();
    }
    //２－封装post请求
    public static void RequestPost(final Map<String,String> params, String url, String tag, final VolleyCallbackListener listener)
    {
        MyApplication.getQueues().cancelAll(tag);//取消之前的请求，防止重复请求
        StringRequest post_request=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        listener.onFinish(s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        listener.onError(volleyError);
                    }
                }
        ){
            @Override//将要提交的数据位于信息头后面的实体中
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        post_request.setTag(tag);//为该请求设置一个tag标志
        MyApplication.getQueues().add(post_request);
        MyApplication.getQueues().start();
    }
}
