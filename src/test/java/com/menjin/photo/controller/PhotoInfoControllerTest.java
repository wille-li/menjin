package com.menjin.photo.controller;

import static org.bytedeco.javacpp.helper.opencv_objdetect.cvHaarDetectObjects;
import static org.bytedeco.javacpp.opencv_core.IPL_DEPTH_8U;
import static org.bytedeco.javacpp.opencv_core.cvClearMemStorage;
import static org.bytedeco.javacpp.opencv_core.cvCopy;
import static org.bytedeco.javacpp.opencv_core.cvCreateImage;
import static org.bytedeco.javacpp.opencv_core.cvGetSeqElem;
import static org.bytedeco.javacpp.opencv_core.cvLoad;
import static org.bytedeco.javacpp.opencv_core.cvPoint;
import static org.bytedeco.javacpp.opencv_core.cvSetImageROI;
import static org.bytedeco.javacpp.opencv_core.cvSize;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;
import static org.bytedeco.javacpp.opencv_imgproc.CV_AA;
import static org.bytedeco.javacpp.opencv_imgproc.CV_BGR2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.cvCvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.cvRectangle;
import static org.bytedeco.javacpp.opencv_objdetect.CV_HAAR_DO_ROUGH_SEARCH;
import static org.bytedeco.javacpp.opencv_objdetect.CV_HAAR_SCALE_IMAGE;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_core.CvMemStorage;
import org.bytedeco.javacpp.opencv_core.CvRect;
import org.bytedeco.javacpp.opencv_core.CvScalar;
import org.bytedeco.javacpp.opencv_core.CvSeq;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacpp.opencv_objdetect.CvHaarClassifierCascade;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
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
    public void testEnterCheck() throws Exception{  
    	String fileName = getFaceFile();
    	FileInputStream fis = new FileInputStream(fileName);
        MockMultipartFile multipartFile = new MockMultipartFile("file", fis);
       

        HashMap<String, String> contentTypeParams = new HashMap<String, String>();
        contentTypeParams.put("boundary", "265001916915724");
        MediaType mediaType = new MediaType("multipart", "form-data", contentTypeParams);

		mockMvc.perform(
        		MockMvcRequestBuilders.fileUpload("/api/enterCheck.do")
        		.file(multipartFile)
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
    
    
    public static String getFaceFile() throws Exception {
    	String faceFilePath = null;
		String classifierName = null;
		// 这个文件放到本地，打开会快一点。
		URL url = new URL("file:///D:/other_workspace/cv/src/main/resources/haarcascade_frontalface_alt.xml");
		
		//URL url = new URL("https://raw.github.com/Itseez/opencv/2.4.0/data/haarcascades/haarcascade_frontalface_alt.xml");
		File file = Loader.extractResource(url, null, "classifier", ".xml");
		file.deleteOnExit();
		classifierName = file.getAbsolutePath();

		Loader.load(opencv_objdetect.class);
		// 加载人脸识别特征分类器
		CvHaarClassifierCascade classifier = new CvHaarClassifierCascade(cvLoad(classifierName));
		if (classifier.isNull()) {
			System.err.println("Error loading classifier file \"" + classifierName + "\".");
			System.exit(1);
		}
		// 网络摄像头用这个
		FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault("rtsp://admin:admin1234@192.168.0.100:554/h264/ch1/main/av_stream");
		
		// 获取摄像头Grabber
		//FrameGrabber grabber = FrameGrabber.createDefault(0);
		grabber.start();
		// opencv 图片格式转换类
		OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();

		// 灰度图抓取 .. 如果没必要用灰度，可以直接用原图
		IplImage grabbedImage = converter.convert(grabber.grab());
		int width = grabbedImage.width();
		int height = grabbedImage.height();
		IplImage grayImage = IplImage.create(width, height, IPL_DEPTH_8U, 1);

		// 获取内存空间
		CvMemStorage storage = CvMemStorage.create();

		// 摄像头内容展示窗口
		CanvasFrame frame = new CanvasFrame("Some Title", CanvasFrame.getDefaultGamma()/grabber.getGamma());
		
		while (frame.isVisible() && (grabbedImage = converter.convert(grabber.grab())) != null) {
			cvClearMemStorage(storage);
			// 把原图转化成灰度图，放到灰度图对象 grayImage 内
			cvCvtColor(grabbedImage, grayImage, CV_BGR2GRAY);
			// 根据特征获取人脸框框位置 cvHaarDetectObjects 方法参数具体作用详情： http://blog.csdn.net/itismelzp/article/details/50378468
			CvSeq faces = cvHaarDetectObjects(grayImage, classifier, storage, 1.1, 3,
					CV_HAAR_SCALE_IMAGE | CV_HAAR_DO_ROUGH_SEARCH);
			int total = faces.total();
			System.out.println("找到" + total + "张脸");
			for (int i = 0; i < total; i++) {
				CvRect rect = new CvRect(cvGetSeqElem(faces, i));
				int x = rect.x(), y = rect.y(), w = rect.width(), h = rect.height();
				// 在图片上加上框框
				cvRectangle(grabbedImage, cvPoint(x, y), cvPoint(x + w, y + h), CvScalar.GREEN, 1, CV_AA, 0);
				// 保存图片抓取到的脸
				IplImage tmpImage = grabbedImage.clone();
				IplImage needSaveFace = detect(tmpImage, rect);
				String resultPath = FACE_PIC_PATH + Calendar.getInstance().getTimeInMillis() + ".jpg";
				cvSaveImage(resultPath, needSaveFace);
				faceFilePath = resultPath;
				break;
			}

			if (faceFilePath != null && faceFilePath.length() > 0) {
				Thread.sleep(3000);
				break;
			}
			// 把图片放到展示框上
			Frame rotatedFrame = converter.convert(grabbedImage);
			frame.showImage(rotatedFrame);
			
			Thread.sleep(100);
		}
		
		frame.dispose();
		grabber.stop();
		return faceFilePath;
	}

	public static IplImage detect(IplImage img, CvRect rect) {
		IplImage dst = cvCreateImage(cvSize(rect.width(), rect.height()), IPL_DEPTH_8U, img.nChannels());
		cvSetImageROI(img, rect);
		cvCopy(img, dst);
		return dst;
	}
}
