package com.menjin.photo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;

public class TestUpload {

	//private static final String ENDPOINT = "http://112.74.84.48/";
	
	private static final String ENDPOINT = "http://localhost:8080/MenJin/";

	private static final Retrofit sRetrofit = new Retrofit.Builder().baseUrl(ENDPOINT)
			.addConverterFactory(GsonConverterFactory.create()).build();

	public static void main(String[] args) {
		//testCompany2();
		//testVisit();
		testUpload2();
		//testVisit();
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
	
	
	public static void testCompany2(){
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
	
	public static void testUpload(){
		UploadService service = sRetrofit.create(UploadService.class);

		File file1 = new File("C:\\Users\\user\\Desktop\\1.jpg");
		File file2 = new File("C:\\Users\\user\\Desktop\\2.jpg");

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
	
	public static void testUpload2(){
		File file1 = new File("C:\\Users\\user\\Desktop\\Personal\\idcard2.jpg");
		File file2 = new File("C:\\Users\\user\\Desktop\\Personal\\idcard.jpg");
		List<File> list = new ArrayList<File>();
		list.add(file1);
		list.add(file2);
		List<MultipartBody.Part> path = filesToMultipartBodyParts(list);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("idCardNum", "126ssd623");
		params.put("visitorName", "csap");
		params.put("birth", "1990年02月09日");
		
		NetWorkService.postUserInfo(params, path, new Observer<HeadBean>(){
		//NetWorkService.postUserInfo("126623", "cap", "1990-02-09", path, new Observer<HeadBean>(){

			@Override
			public void onCompleted() {
				System.out.println(1);				
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(2);	
				e.printStackTrace();
			}

			@Override
			public void onNext(HeadBean t) {
				System.out.println(3);				
			}});
		
	}
	
	public static MultipartBody filesToMultipartBody(List<File> files) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型 
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
            builder.addFormDataPart("file", file.getName(), requestBody);
        }

        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }
	
	public static void testVisit(){
		Map<String,String> map = new HashMap<String,String>();
        map.put("tokenid","tokenid");
        map.put("visitorName","visitorName");
        map.put("idCardNum","126ssd623");
        map.put("mobile","13318498837");
        map.put("companyId","123");
        map.put("employee","“王五”");
        map.put("visitReson","1");
        map.put("appointmentTime","2016-12-21 12:21");
        NetWorkService.postOrder(map , new Observer<HeadBean>(){

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
			public void onNext(HeadBean arg0) {
				// TODO Auto-generated method stub
				System.out.println(3);
			}});
	}
	
	public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<MultipartBody.Part> (files.size());
        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }
}
