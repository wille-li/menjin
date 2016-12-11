package com.menjin.photo.controller;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

public class NetWorkService extends RetrofitUtils{
    public static  NetService service = null;
    //public static String API_URL = "http://localhost:8080/MenJin";
    public static String API_URL = "http://112.74.84.48";
	 //创建实现接口调用
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.immediate())
                .subscribeOn(Schedulers.immediate())//子线程访问网络
                .observeOn(Schedulers.immediate())//回调主线程
                .subscribe(observer);
    }

    public static NetService getNetService(){
        if(service == null ){
            service = getRetrofit(API_URL).create(NetService.class);
        }
        return service;
    }

    private interface NetService{
        @GET("api/getCompany.do")
        Observable<CompanyDto> getCompany(@Query("version") int version, @Query("tokenid") String tokenId);

        @GET("api/checkIDCard.do")
        Observable<HeadBean> isRegister(@Query("idCardNum") String idCardNum, @Query("tokenid") String tokenId);

        @Multipart
        @POST("api/upload.do")
        Observable<HeadBean> postUserInfo(@Part("idCardNum") String idCardNum,
                                          @Part("visitorName")String visitorName,
                                          @Part("birth")String birth,
                                          @PartMap Map<String, RequestBody> file);

        @POST("api/visit.do")
        Observable<HeadBean> postOrder(@QueryMap Map <String,String> map);
    }

    public static void postOrder(Map<String, String> map,Observer<HeadBean> observer){
        setSubscribe(getNetService().postOrder(map),observer);
    }

    public static void postUserInfo(String idCardNum,String visitorName,String birth,Map<String, RequestBody> file,Observer<HeadBean> observer){
        setSubscribe(getNetService().postUserInfo(idCardNum,visitorName,birth,file),observer);
    }
    public static void getCompany(int version,String tokenId,Observer<CompanyDto> observer){
        setSubscribe(getNetService().getCompany(version,tokenId),observer);
    }
    public static void isRegister(String idCardNum,String tokenId,Observer<HeadBean> observer){
        setSubscribe(getNetService().isRegister(idCardNum,tokenId),observer);
    }
}
