package com.menjin.photo.controller;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    public static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    public static Retrofit getRetrofit(String apiUrl) {

        if (null == mRetrofit) {

            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttp3Utils.getmOkHttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(apiUrl + "/")
                    //添加转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置使用okhttp网络请求
                    .client(mOkHttpClient)
                    .build();

        }

        return mRetrofit;
    }

}
