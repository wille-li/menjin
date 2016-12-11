package com.menjin.photo.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttp3Utils {
	private static OkHttpClient mOkHttpClient;
    //设置缓存目录
//    private static File cacheDirectory = new File(MyApplication.getInstance().getApplicationContext().getCacheDir().getAbsolutePath(), "MyCache");
//    private static Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);

    /**
     * @return OkhttpClient
     */
    public static OkHttpClient getmOkHttpClient() {
        if (mOkHttpClient == null) {
        	Builder builder = new OkHttpClient.Builder();
            mOkHttpClient = builder.connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
//                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(interceptor)
                    .build();
        }

        return mOkHttpClient;
    }

    /**
     * 打印日志
     */

    static Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder() 
                    .build();  ;
            LogUtil.e(request.url().toString());
            //开始执行请求时间
            long t1 = System.currentTimeMillis();
            Response response = chain.proceed(request);
            //执行完请求时间
            long t2 = System.currentTimeMillis();

            
            String content = response.body().string();
//            if((t2-t1)<3000){
//                sleep();
//            }
            LogUtil.e(content + "\nurl=" + response.request().url() + "\n" + response.headers()+"\ntime="+(t2-t1));

            return response.newBuilder().body(ResponseBody.create(response.body().contentType(), content)).build();
        }
    };

    /**
     * 网速太快！！！不延迟体现不了刷新的效果
     */
    static void sleep(){
        try{
            Thread.currentThread().sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
