package com.example.achuan.mvp_study;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.achuan.mvp_study.app.MyApplication;
import com.example.achuan.mvp_study.model.http.VolleyCallbackListener;
import com.example.achuan.mvp_study.model.http.VolleyRequestUtil;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.iv_img)
    ImageView mIvImg;
    @BindView(R.id.networkImageView)
    NetworkImageView mNetworkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        /*图片加载*/
        String url="https://www.baidu.com/img/bdlogo.png";
       /* //1-ImageRequest方式加载图片
        ImageRequest imageRequest=new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        mIvImg.setImageBitmap(bitmap);
                    }
                },
                0,//图片的宽度
                0,//图片的高度（设置为０将按照原图大小来显示）
                Bitmap.Config.RGB_565,//加载图片的格式
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                    }
                }

        );
        MyApplication.getQueues().add(imageRequest);*/
        /*//２－ImageLoader方式加载图片
        ImageLoader imageRequest=new ImageLoader(
                MyApplication.getQueues(),//请求队列
                new BitmpCache());
        ImageLoader.ImageListener imageListener=  ImageLoader.getImageListener(
                mIvImg,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        imageRequest.get(url, imageListener);*/
        //3-NetworkImageView方法进行图片加载
        ImageLoader imageRequest=new ImageLoader(
                MyApplication.getQueues(),//请求队列
                new BitmpCache());
        mNetworkImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        mNetworkImageView.setErrorImageResId(R.mipmap.ic_launcher);
        mNetworkImageView.setImageUrl(url,imageRequest);


    }
    //3-volley与activity的联动,活动关闭时关闭请求
    @Override
    protected void onStop() {
        super.onStop();
        //MyApplication.getQueues().cancelAll("abcPost");//通过tag将指定的请求线程关闭
    }

    //２－post请求方式
    private void volley_Post() {
        String url = "http://apis.juhe.cn/mobile/get?";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("phone", "13429667914");
        map.put("key", "");
        JSONObject object = new JSONObject(map);//将数据转换成Json格式的数据
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                object,//将要提交的数据位于信息头后面的实体中
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                }
        );
        request.setTag("abcPost");//为该请求设置一个tag标志
        MyApplication.getQueues().add(request);
    }

    //１－get请求方式
    private void volley_Get(String url, String tag) {
        VolleyRequestUtil.RequestGet(url, tag, new VolleyCallbackListener() {
            @Override
            public void onFinish(String response) {

            }
            @Override
            public void onError(Exception e) {

            }
        });
    }
}
