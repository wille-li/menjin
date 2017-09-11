package com.menjin.face.service;

import static org.bytedeco.javacpp.helper.opencv_objdetect.cvHaarDetectObjects;
import static org.bytedeco.javacpp.opencv_core.IPL_DEPTH_8U;
import static org.bytedeco.javacpp.opencv_core.cvClearMemStorage;
import static org.bytedeco.javacpp.opencv_core.cvCopy;
import static org.bytedeco.javacpp.opencv_core.cvCreateImage;
import static org.bytedeco.javacpp.opencv_core.cvGetSeqElem;
import static org.bytedeco.javacpp.opencv_core.cvLoad;
import static org.bytedeco.javacpp.opencv_core.cvSetImageROI;
import static org.bytedeco.javacpp.opencv_core.cvSize;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;
import static org.bytedeco.javacpp.opencv_imgproc.CV_BGR2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.cvCvtColor;
import static org.bytedeco.javacpp.opencv_objdetect.CV_HAAR_DO_ROUGH_SEARCH;
import static org.bytedeco.javacpp.opencv_objdetect.CV_HAAR_FIND_BIGGEST_OBJECT;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_core.CvMemStorage;
import org.bytedeco.javacpp.opencv_core.CvRect;
import org.bytedeco.javacpp.opencv_core.CvSeq;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacpp.opencv_objdetect.CvHaarClassifierCascade;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.menjin.camera.model.Camera;
import com.menjin.camera.service.CameraService;
import com.menjin.face.model.CheckFaceTask;
import com.menjin.face.model.FaceInfo;

@Component
@EnableScheduling
public class CameraMonitorTask {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	CameraService cameraService;

	@Resource
	FaceClient faceClient;

	@Value("${photo.path}")
	String photoPath;

	private ExecutorService executor = Executors.newFixedThreadPool(3);

	CvHaarClassifierCascade classifier;

	static Map<FFmpegFrameGrabber, Camera> grabberMap = new HashMap<>();

	Queue<FFmpegFrameGrabber> queue = new LinkedList<FFmpegFrameGrabber>();

	CvMemStorage storage = CvMemStorage.create();

	OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();

	//@PostConstruct
	private void initCameraList() {
		List<Camera> cameraList = cameraService.findByPage(null, null, null);
		String classifierName = null;
		try {
			URL url = new URL("file:///D:/other_workspace/cv/src/main/resources/haarcascade_frontalface_alt.xml");
			File file = Loader.extractResource(url, null, "classifier", ".xml");
			file.deleteOnExit();
			classifierName = file.getAbsolutePath();
		} catch (IOException e) {
			logger.error("读取配置出错", e);
		}
		// Preload the opencv_objdetect module to work around a known bug.
		Loader.load(opencv_objdetect.class);

		// We can "cast" Pointer objects by instantiating a new object of the
		// desired class.
		this.classifier = new CvHaarClassifierCascade(cvLoad(classifierName));
		if (classifier.isNull()) {
			logger.error("Error loading classifier file \"" + classifierName + "\".");
		}

		for (Camera camera : cameraList) {
			String ip = camera.getIp();
			try {
				FFmpegFrameGrabber grabber = null;
				if (ip != null & ip.startsWith("0")) {
					grabber = FFmpegFrameGrabber.createDefault(0);
				} else {
					StringBuffer rtspUrl = new StringBuffer("rtsp://");
					rtspUrl.append(camera.getUsername()).append(":").append(camera.getPassword()).append("@")
							.append(camera.getIp()).append(":").append(camera.getPort())
							.append("/h264/ch1/main/av_stream");
					logger.info(rtspUrl.toString());
					//grabber = FFmpegFrameGrabber.createDefault(rtspUrl.toString());
				}
				if (grabber != null) {
					grabberMap.put(grabber, camera);
					grabber.start();
					queue.offer(grabber);
				}
			} catch (Exception e) {
				logger.error("打开摄像头错误", e);
			}
		}

	}

	//@Scheduled(cron = "0/3 * * * * ?")
	public void monitor() {
		logger.info("定时任务" + Calendar.getInstance().getTime());

		FFmpegFrameGrabber grabber = queue.peek();
		if (grabber == null) {
			logger.info("队列里没有Grabber");
			return ;
		}

		Camera camera = grabberMap.get(grabber);
		String fileName = photoPath + camera.getIp() + Calendar.getInstance().getTimeInMillis() + ".jpg";
		try {
			IplImage grabbedImage = converter.convert(grabber.grab());
			int width = grabbedImage.width();
			int height = grabbedImage.height();
			IplImage grayImage = IplImage.create(width, height, IPL_DEPTH_8U, 1);
			cvClearMemStorage(storage);
			cvCvtColor(grabbedImage, grayImage, CV_BGR2GRAY);
			CvSeq faces = cvHaarDetectObjects(grayImage, classifier, storage, 1.1, 3,
					CV_HAAR_FIND_BIGGEST_OBJECT | CV_HAAR_DO_ROUGH_SEARCH);
			int total = faces.total();

			for (int i = 0; i < total; i++) {
				FrameGrabber tmpGrabber = queue.poll();
				CvRect rect = new CvRect(cvGetSeqElem(faces, i));
				IplImage faceImg = detect(grayImage, rect);
				cvSaveImage(fileName, faceImg);
				Future<FaceInfo> future = executor.submit(new CheckFaceTask(fileName, "visitor", faceClient, tmpGrabber));
				FaceInfo faceInfo = future.get();
				if (faceInfo.getScope() > 80) {
					logger.info(faceInfo.toString());
					logger.info("有这张脸");
				} else {
					logger.info(faceInfo.toString());
					logger.info("没有这张脸");
				}
				
			}
		} catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
			logger.error("打开摄像头错误", e);
		} catch (InterruptedException e) {
			logger.error("多线程终端", e);
		} catch (ExecutionException e) {
			logger.error("执行器终端", e);
		} catch (Exception e) {
			logger.error("异步任务存在错误", e);
		} finally {
			queue.offer(grabber);
		}

	}

	private IplImage detect(IplImage img, CvRect rect) {
		IplImage dst = cvCreateImage(cvSize(rect.width(), rect.height()), IPL_DEPTH_8U, img.nChannels());
		cvSetImageROI(img, rect);
		cvCopy(img, dst);
		return dst;
	}

	@PreDestroy
	private void distory() {
		Set<Entry<FFmpegFrameGrabber, Camera>> set = grabberMap.entrySet();
		for (Entry<FFmpegFrameGrabber, Camera> entry : set) {
			try {
				entry.getKey().stop();
			} catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
				logger.error("销毁对象失败", e);
			}
		}
	}

}
