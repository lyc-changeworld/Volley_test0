package com.example.achuan.mvp_study;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by achuan on 16-9-22.
 */
public class BitmpCache implements ImageLoader.ImageCache {
    //创建一个队列来进行图片的缓存(如果队列满了,将执行先进先出规则)
    //前面的string为key值,bitmap为具体的图片对象
    public LruCache<String,Bitmap> mBitmapLruCache;//声明引用变量
    //定义一张图片缓存的最大空间,超过这个大小,将启动自动回收
    public int max=10*1024*1024;//10*1M＝10M
    //构造器
    public BitmpCache() {
        mBitmapLruCache=new LruCache<String, Bitmap>(max){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //返回图片的大小
                return value.getRowBytes()*value.getHeight();//每行的大小*行数
            }
        };
    }
    //获取队列中的图片
    @Override
    public Bitmap getBitmap(String s) {
        return mBitmapLruCache.get(s);
    }
    //添加图片到队列中
    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        mBitmapLruCache.put(s,bitmap);
    }
}
