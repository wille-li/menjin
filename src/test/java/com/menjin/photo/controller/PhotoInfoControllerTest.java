package com.menjin.photo.controller;



import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration  
@ContextConfiguration({"classpath:spring/spring.xml","classpath:spring/spring-mvc.xml"}) 
@RunWith(SpringJUnit4ClassRunner.class)  
@Transactional 
@Rollback(false)
public class PhotoInfoControllerTest {

	private static final String FACE_PIC_PATH = "C:/pic/";
	
    @Autowired  
    private WebApplicationContext webApplicationContext;  
    
    private MockMvc mockMvc;  
    
    @Before  
    public void init(){  
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();  
    }
    
    
    
    /*测试文件上传发送的请求*/  
    @Test  
    public void testUpload() throws Exception{  
    	FileInputStream fis = new FileInputStream("C:\\Users\\user\\Desktop\\Personal\\test4.jpg");
        MockMultipartFile multipartFile = new MockMultipartFile("file", fis);
       

        HashMap<String, String> contentTypeParams = new HashMap<String, String>();
        contentTypeParams.put("boundary", "265001916915724");
        MediaType mediaType = new MediaType("multipart", "form-data", contentTypeParams);

		mockMvc.perform(
        		MockMvcRequestBuilders.fileUpload("/api/upload.do")
        		.file(multipartFile)
        		.param("idCardNum", "440982199003031230")
        		.param("visitorName", "黎某某")
                .contentType(mediaType));  
    } 
    

    @Test  
    public void testvisitor() throws Exception{  


        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/visit.do")
                .param("idCardNum", "440982199003031230")
                .param("phoneNum", "13510444833")
                .param("appointmentTime", "2017-07-07 10:10")
                .param("companyId", "1")
                .param("matterId", "554")
                .param("employeeName", "程凡")
                .param("employeePhone", "13510444833")
                );  
    } 
    

}
