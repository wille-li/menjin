package com.menjin.photo.controller;

import java.io.FileInputStream;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.base.annotation.log.SystemControllerLog;

@RunWith(SpringJUnit4ClassRunner.class)  
@WebAppConfiguration  
@ContextConfiguration({"classpath:spring/spring-mvc.xml","classpath:spring/spring.xml"}) 
@Transactional 
@Rollback(false)
public class PhotoInfoControllerTest {

	@Value("${photo.path}")
	private String filePath;
	
    @Autowired  
    private WebApplicationContext webApplicationContext;  
    private MockMvc mockMvc;  
    @Before  
    public void init(){  
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();  
    }
    
    /*测试文件上传发送的请求*/  
    @Test  
    @SystemControllerLog
    public void testUpload() throws Exception{  
    	FileInputStream fis = new FileInputStream("/Users/Wille/Desktop/image.jpg");
        MockMultipartFile multipartFile = new MockMultipartFile("file", fis);

        HashMap<String, String> contentTypeParams = new HashMap<String, String>();
        contentTypeParams.put("boundary", "265001916915724");
        MediaType mediaType = new MediaType("multipart", "form-data", contentTypeParams);

        mockMvc.perform(
        		MockMvcRequestBuilders.fileUpload("/api/upload.do").file(multipartFile).
                content(multipartFile.getBytes())
                .contentType(mediaType))
                .andExpect(MockMvcResultMatchers.status().isOk());  
    } 
}
