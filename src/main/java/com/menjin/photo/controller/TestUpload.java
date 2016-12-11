package com.menjin.photo.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;

public class TestUpload {

	private static final String ENDPOINT = "http://112.74.84.48/";
	
	///private static final String ENDPOINT = "http://127.0.0.1:8080/MenJin/";

	private static final Retrofit sRetrofit = new Retrofit.Builder().baseUrl(ENDPOINT)
			.addConverterFactory(GsonConverterFactory.create()).build();

	public static void main(String[] args) {
		testGetCompany();
		NetWorkService.getCompany(1, "1", new Observer<CompanyDto>(){

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println(1);
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println(2);
				LogUtil.e(arg0);
				arg0.printStackTrace();
			}

			@Override
			public void onNext(CompanyDto arg0) {
				// TODO Auto-generated method stub
				System.out.println(3);
			}});
	}

	public static void testGetCompany() {
		UploadService service = sRetrofit.create(UploadService.class);
		try {
			Call<Gson> result = service.getCompany(0);
			Response<Gson> rsp = result.execute();
			// Response rsp = call.execute();
			System.out.println(rsp.isSuccessful());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void testUpload(){
		UploadService service = sRetrofit.create(UploadService.class);

		File file1 = new File("C:\\Users\\Administrator\\Desktop\\1.jpg");
		File file2 = new File("C:\\Users\\Administrator\\Desktop\\2.jpg");

		RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
		RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);

		Map<String, RequestBody> map = new HashMap<String, RequestBody>();
		map.put("file", requestBody1);
		map.put("file", requestBody2);
		
		try {
			Call<Gson> result = service.uploadImage("123123123123123", "sdsd", map);
			Response<Gson> rsp = result.execute();
			// Response rsp = call.execute();
			System.out.println(rsp.isSuccessful());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
