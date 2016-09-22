package com.example.achuan.mvp_study.model.http;

/**
 * Created by achuan on 16-9-21.
 */
public interface VolleyCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
