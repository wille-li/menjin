/*package com.menjin.afr.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinovoice.hcicloudsdk.api.HciCloudSys;
import com.sinovoice.hcicloudsdk.api.HciCloudUser;
import com.sinovoice.hcicloudsdk.api.HciLibPath;
import com.sinovoice.hcicloudsdk.api.afr.HciCloudAfr;
import com.sinovoice.hcicloudsdk.common.HciErrorCode;
import com.sinovoice.hcicloudsdk.common.Session;
import com.sinovoice.hcicloudsdk.common.afr.AfrConfig;
import com.sinovoice.hcicloudsdk.common.afr.AfrDetectFace;
import com.sinovoice.hcicloudsdk.common.afr.AfrDetectFaceAttribute;
import com.sinovoice.hcicloudsdk.common.afr.AfrDetectFacebox;
import com.sinovoice.hcicloudsdk.common.afr.AfrDetectGenderAttribute;
import com.sinovoice.hcicloudsdk.common.afr.AfrDetectLandmark;
import com.sinovoice.hcicloudsdk.common.afr.AfrDetectLeftEyeAttribute;
import com.sinovoice.hcicloudsdk.common.afr.AfrDetectMouthAttribute;
import com.sinovoice.hcicloudsdk.common.afr.AfrDetectPoseAttribute;
import com.sinovoice.hcicloudsdk.common.afr.AfrDetectResult;
import com.sinovoice.hcicloudsdk.common.afr.AfrDetectRightEyeAttribute;
import com.sinovoice.hcicloudsdk.common.afr.AfrDetectSkinAttribute;
import com.sinovoice.hcicloudsdk.common.afr.AfrEnrollResult;
import com.sinovoice.hcicloudsdk.common.afr.AfrIdentifyResult;
import com.sinovoice.hcicloudsdk.common.afr.AfrIdentifyResultItem;
import com.sinovoice.hcicloudsdk.common.afr.AfrInitParam;
import com.sinovoice.hcicloudsdk.common.afr.AfrVerifyResult;

public class HciCloudAfrHelper {
	private String mCapKey = AccountInfo.getInstance().getCapKey();
	private static HciCloudAfrHelper mInstance;

	public static String SYS_PATH =  "C:\\menjin\\git\\MenJin\\src\\main\\resources";
	
	
	public static String PIC_PATH = "C:\\pic";
	static {
		// 灵云sdk中dll文件目录
		String libPath = SYS_PATH + "\\libs";

		// 指定dll文件路径，顺序表示加载顺序；
		String afrLibPath[] = new String[] { libPath + "\\libhci_curl.dll",
				libPath + "\\hci_sys.dll", libPath + "\\hci_afr.dll",
				libPath + "\\hci_afr_jni.dll",
				libPath + "\\hci_afr_cloud_recog.dll",
				libPath + "\\mkl_core.dll",
				libPath + "\\mkl_sequential.dll",
				libPath + "\\mkl_rt.dll",
				libPath + "\\hci_afr_local_recog.dll",
				};

		HciLibPath.setAfrLibPath(afrLibPath);
	}

	private HciCloudAfrHelper() {
	}

	public static HciCloudAfrHelper getInstance() {
		if (mInstance == null) {
			mInstance = new HciCloudAfrHelper();
		}
		return mInstance;
	}

	*//**
	 * Afr引擎初始化 辅助工具 : AfrInitParam:该类的实例通过addParam(key, value)的方式添加Afr初始化的参数,
	 * 再通过getStringConfig() 获取初始化时需要的字符串参数 config 初始化方法:
	 * HciCloudAfr.hciAfrInit(config)
	 *//*
	public int init() {
		// 构造Afr初始化的帮助类的实例
		AfrInitParam initParam = new AfrInitParam();

		// 获取App应用中的lib的路径,放置能力所需资源文件。
		String dataPath = SYS_PATH + "\\data";

		initParam.addParam(AfrInitParam.PARAM_KEY_DATA_PATH, dataPath);

		// 此处演示初始化的能力为Afr.local.verify, 用户可以根据自己可用的能力进行设置, 另外,此处可以传入多个能力值,并用;隔开
		// 如 "Afr.local.verify;Afr.local.verify.telephone"
		initParam.addParam(AfrInitParam.PARAM_KEY_INIT_CAP_KEYS, mCapKey);
		initParam.addParam(AfrInitParam.PARAM_KEY_FILE_FLAG, "none");
		logger.info("Afr initParam:" + initParam.getStringConfig());
		// 调用初始化方法
		int initResult = HciCloudAfr.hciAfrInit(initParam.getStringConfig());

		return initResult;
	}

	*//**
	 * Afr的反初始化和系统的反初始化, 在Init方法成功后, 执行结束时需要调用反初始化, 否则会导致引擎没有停止运行, 在下一次运行时会出现返回
	 * HCI_ERR_SYS_ALREADY_INIT 或其他错误信息
	 *//*
	public int release() {
		// Afr反初始化
		int nRet = HciCloudAfr.hciAfrRelease();

		return nRet;
	}

	
	 * AFR检测(Detect)
	 
	public static boolean Detect(String capkey, String filename,
			ArrayList<String> faceIds) {
		logger.info("afrDetect enter...");

		// 加载人脸图片
		byte[] buffer = getFileData(filename);
		if (buffer.length == 0) {
			logger.info("Open input iamge file" + filename + "error!");
			return false;
		}
		// 开始会话
		AfrConfig sessionConfig = new AfrConfig();
		sessionConfig.addParam(AfrConfig.SessionConfig.PARAM_KEY_CAP_KEY,
				capkey);

		logger.info("hciAfrSessionStart config: "
				+ sessionConfig.getStringConfig());
		Session session = new Session();
		int errCode = HciCloudAfr.hciAfrSessionStart(
				sessionConfig.getStringConfig(), session);
		if (HciErrorCode.HCI_ERR_NONE != errCode) {
			logger.info("hciAfrSessionStart return " + errCode);
			return false;
		}
		logger.info("hciAfrSessionStart Success");

		// 设置检测图像
		errCode = HciCloudAfr.hciAfrSetImageBuffer(session, buffer);
		if (HciErrorCode.HCI_ERR_NONE != errCode) {
			HciCloudAfr.hciAfrSessionStop(session);
			logger.info("hciAfrSetImageBuffer error.");
			return false;
		}

		// AFR检测
		String detectConfig = "";
		AfrDetectResult detectResult = new AfrDetectResult();
		errCode = HciCloudAfr.hciAfrDetect(session, detectConfig, detectResult);
		if (HciErrorCode.HCI_ERR_NONE != errCode) {
			logger.info("face detect error");
			return false;
		}
		printDetectResult(detectConfig,detectResult);
		for (AfrDetectFace face : detectResult.getFaceList()) {
			faceIds.add(face.getFaceId());
		}
		
		// 释放检测结果。
		HciCloudAfr.hciAfrFreeDetectResult(detectResult);

		// 关闭会话
		errCode = HciCloudAfr.hciAfrSessionStop(session);
		if (HciErrorCode.HCI_ERR_NONE != errCode) {
			logger.info("session stop error.");
			return false;
		}
		logger.info("afrDetect leave...");

		return true;
	}

	
	 * AFR注册(Enroll)
	 
	public static boolean Enroll(String capkey, AfrConfig enrollConfig,
			String filename, AfrEnrollResult result) {

		logger.info("afrEnroll enter...");

		ArrayList<String> faceIds = new ArrayList<String>();

		// 加载人脸图片
		byte[] buffer = getFileData(filename);
		if (buffer.length == 0) {
			logger.info("Open input iamge file" + filename + "error!");
			return false;
		}

		// 开始会话
		AfrConfig sessionConfig = new AfrConfig();
		sessionConfig.addParam(AfrConfig.SessionConfig.PARAM_KEY_CAP_KEY,
				capkey);

		logger.info("hciAfrSessionStart config: "
				+ sessionConfig.getStringConfig());
		Session session = new Session();
		int errCode = HciCloudAfr.hciAfrSessionStart(
				sessionConfig.getStringConfig(), session);
		if (HciErrorCode.HCI_ERR_NONE != errCode) {
			logger.info("hciAfrSessionStart return " + errCode);
			return false;
		}
		logger.info("hciAfrSessionStart Success");

		// 设置检测图像
		errCode = HciCloudAfr.hciAfrSetImageBuffer(session, buffer);
		if (HciErrorCode.HCI_ERR_NONE != errCode) {
			HciCloudAfr.hciAfrSessionStop(session);
			logger.info("hciAfrSetImageBuffer error.");
			return false;
		}

		// AFR检测
		String detectConfig = "";
		AfrDetectResult detectResult = new AfrDetectResult();
		errCode = HciCloudAfr.hciAfrDetect(session, detectConfig, detectResult);
		if (HciErrorCode.HCI_ERR_NONE != errCode) {
			logger.info("face detect error");
			return false;
		}
		printDetectResult(detectConfig,detectResult);
		for (AfrDetectFace face : detectResult.getFaceList()) {
			faceIds.add(face.getFaceId());
		}
		
		if (faceIds.size() > 0)
		{
			// 选择注册的faceid
			enrollConfig.addParam("faceid", faceIds.get(0));
		}

		// AFR 注册
		logger.info("hciAfrEnroll config:" + enrollConfig.getStringConfig());
		errCode = HciCloudAfr.hciAfrEnroll(session,
				enrollConfig.getStringConfig(), result);
		if (HciErrorCode.HCI_ERR_NONE != errCode) {
			HciCloudAfr.hciAfrSessionStop(session);
			logger.info("hciAfrEnroll return " + errCode);
			return false;
		}
		logger.info("hciAfrEnroll Success");
		logger.info("enroll result is:" + result.getUserId());
		
		// 释放检测结果。
		HciCloudAfr.hciAfrFreeDetectResult(detectResult);

		// 关闭会话
		HciCloudAfr.hciAfrSessionStop(session);
		logger.info("hciAfrSessionStop Success");

		logger.info("afrEnroll leave...");
		return true;
	}

	
	 * AFR 确认（Verify）
	 
	public static boolean Verify(String capkey, String filename,
			AfrConfig verifyConfig, AfrVerifyResult result) {
		logger.info("afrVerify enter...");

		ArrayList<String> faceIds = new ArrayList<String>();

		// 加载人脸图片
		byte[] buffer = getFileData(filename);
		if (buffer.length == 0) {
			logger.info("Open input iamge file" + filename + "error!");
			return false;
		}

		// 开始会话
		AfrConfig sessionConfig = new AfrConfig();
		sessionConfig.addParam(AfrConfig.SessionConfig.PARAM_KEY_CAP_KEY,
				capkey);
		logger.info("hciAfrSessionStart config: "
				+ sessionConfig.getStringConfig());
		Session session = new Session();
		int errCode = HciCloudAfr.hciAfrSessionStart(
				sessionConfig.getStringConfig(), session);
		if (HciErrorCode.HCI_ERR_NONE != errCode) {
			logger.info("hciAfrSessionStart return " + errCode);
			return false;
		}
		logger.info("hciAfrSessionStart Success");

		// 设置检测图像
		errCode = HciCloudAfr.hciAfrSetImageBuffer(session, buffer);
		if (HciErrorCode.HCI_ERR_NONE != errCode) {
			HciCloudAfr.hciAfrSessionStop(session);
			logger.info("hciAfrSetImageBuffer error.");
			return false;
		}

		// AFR检测
		String detectConfig = "";
		AfrDetectResult detectResult = new AfrDetectResult();
		errCode = HciCloudAfr.hciAfrDetect(session, detectConfig, detectResult);
		if (HciErrorCode.HCI_ERR_NONE != errCode) {
			logger.info("face detect error");
			return false;
		}
		printDetectResult(detectConfig,detectResult);
		for (AfrDetectFace face : detectResult.getFaceList()) {
			faceIds.add(face.getFaceId());
		}
		
		if (faceIds.size() > 0)
		{
			// 选择验证的faceid
			verifyConfig.addParam("faceid", faceIds.get(0));
		}

		// 开始确认
		logger.info("hciAfrVerify config:" + verifyConfig.getStringConfig());
		errCode = HciCloudAfr.hciAfrVerify(session,
				verifyConfig.getStringConfig(), result);
		if (HciErrorCode.HCI_ERR_NONE != errCode) {
			logger.info("Hciafr hciAfrVerify return " + errCode);
			HciCloudAfr.hciAfrSessionStop(session);
			return false;
		}
		logger.info("hciAfrVerify Success");
		boolean resultFlag = false;
		if (result.getStatus() == AfrVerifyResult.AFR_VERIFY_STATUS_MATCH) {
			logger.info("face data matches with user_id !");
			resultFlag =  true;
		} else {
			logger.info("face data doesn't match with user_id !");
		}
		
		// 释放检测结果。
		HciCloudAfr.hciAfrFreeDetectResult(detectResult);

		// 关闭会话
		HciCloudAfr.hciAfrSessionStop(session);
		logger.info("hciAfrSessionStop Success");

		logger.info("afrVerify leave...");
		return resultFlag;
	}

	
	 * AFR 辨识（Identify）
	 
	public static boolean Identify(String capkey, AfrConfig identifyConfig,
			String filename, AfrIdentifyResult result) {

		logger.info("afrIdentify enter...");

		ArrayList<String> faceIds = new ArrayList<String>();

		// 加载人脸图片
		byte[] buffer = getFileData(filename);
		if (buffer.length == 0) {
			logger.info("Open input iamge file" + filename + "error!");
			return false;
		}

		// 启动会话
		AfrConfig sessionConfig = new AfrConfig();
		sessionConfig.addParam(AfrConfig.SessionConfig.PARAM_KEY_CAP_KEY,
				capkey);
		logger.info("hciAfrSessionStart config: "
				+ sessionConfig.getStringConfig());
		Session session = new Session();
		int errCode = HciCloudAfr.hciAfrSessionStart(
				sessionConfig.getStringConfig(), session);
		if (HciErrorCode.HCI_ERR_NONE != errCode) {
			logger.info("hciAfrSessionStart return " + errCode);
			return false;
		}
		logger.info("hciAfrSessionStart Success");

		// 设置检测图像
		errCode = HciCloudAfr.hciAfrSetImageBuffer(session, buffer);
		if (HciErrorCode.HCI_ERR_NONE != errCode) {
			HciCloudAfr.hciAfrSessionStop(session);
			logger.info("hciAfrSetImageBuffer error.");
			return false;
		}

		// AFR检测
		String detectConfig = "";
		AfrDetectResult detectResult = new AfrDetectResult();
		errCode = HciCloudAfr.hciAfrDetect(session, detectConfig, detectResult);
		if (HciErrorCode.HCI_ERR_NONE != errCode) {
			logger.info("face detect error");
			return false;
		}
		printDetectResult(detectConfig,detectResult);
		for (AfrDetectFace face : detectResult.getFaceList()) {
			faceIds.add(face.getFaceId());
		}
		
		if (faceIds.size() > 0)
		{
			// 选择识辨的faceid
			identifyConfig.addParam(AfrConfig.SessionConfig.PARAM_KEY_FACE_ID, faceIds.get(0));
		}

		// 识辨
		logger.info("hciAfrIdentify config:" + identifyConfig.getStringConfig());
		errCode = HciCloudAfr.hciAfrIdentify(session,
				identifyConfig.getStringConfig(), result);
		if (HciErrorCode.HCI_ERR_NONE != errCode) {
			logger.info("Hciafr hciAfrIdentify return " + errCode);
			HciCloudAfr.hciAfrSessionStop(session);
			return false;
		} else {
			logger.info("hciAfrIdentify Success");
			// 显示辨识结果
			for (AfrIdentifyResultItem item : result
					.getIdentifyResultItemList()) {
				String identifyInfo = String
						.format("identify user(%s,%d) in group(%s)\n",
								item.getUserId(),
								item.getScore(),
								identifyConfig
										.getParam(AfrConfig.UserConfig.PARAM_KEY_GROUP_ID));
				logger.info(identifyInfo);
			}
		}
		
		// 释放检测结果。
		HciCloudAfr.hciAfrFreeDetectResult(detectResult);

		// 关闭会话
		HciCloudAfr.hciAfrSessionStop(session);
		logger.info("hciAfrSessionStop Success");

		logger.info("afrIdentify leave...");
		return true;
	}
	
	public boolean Func(String capkey, String file1, String file2, String uid) {
		// 初始化AFR
		// 构造AFR初始化的帮助类的实例
		AfrInitParam initParam = new AfrInitParam();
		String dataPath = SYS_PATH + "\\data";
		initParam.addParam(AfrInitParam.PARAM_KEY_DATA_PATH, dataPath);
		initParam.addParam(AfrInitParam.PARAM_KEY_INIT_CAP_KEYS, capkey);
		logger.info("HciAfrInit config :" + initParam.getStringConfig());
		int errCode = HciCloudAfr.hciAfrInit(initParam.getStringConfig());
		if (errCode != HciErrorCode.HCI_ERR_NONE) {
			logger.info("HciAfrInit error:"
					+ HciCloudSys.hciGetErrorInfo(errCode));
			return false;
		} else {
			logger.info("HciAfrInit Success");
		}

		// AFR检测
		ArrayList<String> faceIds = new ArrayList<String>();
		String faceImage = PIC_PATH + "\\" + file1;
		if (!Detect(capkey, faceImage, faceIds)) {
			logger.info("aft detect failed.");
		}

		// 模型保存路径。
		String userModelPath = null;

		// AFR注册
		AfrEnrollResult enrollResult = new AfrEnrollResult();
		AfrConfig enrollConfig = new AfrConfig();
		enrollConfig.addParam(AfrConfig.UserConfig.PARAM_KEY_USER_ID, uid);
		
		if (userModelPath != null) {
			enrollConfig.addParam(
					AfrConfig.SessionConfig.PARAM_KEY_USER_MODEL_PATH,
					userModelPath);
		}
		if (Enroll(capkey, enrollConfig, faceImage, enrollResult)) {
			logger.info("AfrEnroll success!");
			logger.info("Enroll user_id:" + enrollResult.getUserId());
		} else {
			logger.info("afrEnroll failed!");
			return false;
		}

		// AFR确认
		faceImage = PIC_PATH + "\\" + file2;
		AfrConfig verifyConfig = new AfrConfig();
		verifyConfig.addParam(AfrConfig.UserConfig.PARAM_KEY_USER_ID, uid);
		
		if (userModelPath != null) {
			verifyConfig.addParam(
					AfrConfig.SessionConfig.PARAM_KEY_USER_MODEL_PATH,
					userModelPath);
		}
		AfrVerifyResult verifyResult = new AfrVerifyResult();
		boolean resultFlag = Verify(capkey, faceImage, verifyConfig, verifyResult);
		// 反初始化AFR
		HciCloudAfr.hciAfrRelease();
		logger.info("hciAfrRelease");
		return resultFlag;
	}
	
	*//**
	 * 获取文件中的数据
	 * 
	 * @param fileName
	 * @return
	 *//*
	private static byte[] getFileData(String fileName) {
		
		File fileSrc = new File(fileName);
		if (!fileSrc.exists())
			return null;

		int size = (int) fileSrc.length();
		try {
			FileInputStream rd = new FileInputStream(fileSrc);
			byte[] data = new byte[size];
			rd.read(data);
			rd.close();
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return null;
		}
	}

	private static void printDetectResult(String config, AfrDetectResult result) {
		logger.info("print dected result");
		if (result == null)
			return;

		// 显示人脸检测结果及属性
		for (int i = 0; i < result.getFaceList().size(); i++) {
			logger.info("face id = " + result.getFaceList().get(i).getFaceId());
			AfrDetectFacebox facebox = result.getFaceList().get(i).getFacebox();
			String faceboxInfo = String.format("face pos = [%d,%d,%d,%d]\n",
					facebox.getLeft(), facebox.getTop(), facebox.getRight(),
					facebox.getBottom());
			logger.info(faceboxInfo);
			
			for (AfrDetectLandmark landmark : result.getFaceList().get(i).getLandmarkList()) {
				logger.info("landmark x = " + landmark.getX() + ", y = "
						+ landmark.getY());
			}
			
			for (int j = 0; j < result.getFaceList().get(i).getAttributeList().size(); j++) {
				AfrDetectFaceAttribute attribute = result.getFaceList().get(i).getAttributeList().get(j);
				if (attribute instanceof AfrDetectGenderAttribute && !config.contains("gender=no")) {
					int gender = ((AfrDetectGenderAttribute) attribute).getGender();
					String genderInfo;
					if (gender == AfrDetectGenderAttribute.Male) {
						genderInfo = "Male";
					}
					else if (gender == AfrDetectGenderAttribute.Female) {
						genderInfo = "Female";
					}
					else {
						genderInfo = "NotSet";
					}
					logger.info("gender is " + genderInfo);
				}
				else if (attribute instanceof AfrDetectSkinAttribute && config.contains("skin=yes")) {
					int color = ((AfrDetectSkinAttribute) attribute).getSkin();
					String skinInfo;
					if (color == AfrDetectSkinAttribute.Yellow) {
						skinInfo = "Yellow";
					}
					else if (color == AfrDetectSkinAttribute.White) {
						skinInfo = "White";
					}
					else if (color == AfrDetectSkinAttribute.Black) {
						skinInfo = "Black";
					}
					else {
						skinInfo = "NotSet";
					}
					logger.info("skin is " + skinInfo);
				}
				else if (attribute instanceof AfrDetectLeftEyeAttribute && config.contains("eyemouth=yes")) {
					int status = ((AfrDetectLeftEyeAttribute) attribute).getLeftEye();
					String leftEyeInfo;
					if (status == AfrDetectLeftEyeAttribute.Open) {
						leftEyeInfo = "Open";
					}
					else if (status == AfrDetectLeftEyeAttribute.Close) {
						leftEyeInfo = "Close";
					}
					else {
						leftEyeInfo = "NotSet";
					}
					logger.info("left eye is " + leftEyeInfo);
				}
				else if (attribute instanceof AfrDetectMouthAttribute && config.contains("eyemouth=yes")) {
					int status = ((AfrDetectMouthAttribute) attribute).getMouth();
					String mouthInfo;
					if (status == AfrDetectMouthAttribute.Open) {
						mouthInfo = "Open";
					}
					else if (status == AfrDetectMouthAttribute.Close) {
						mouthInfo = "Close";
					}
					else {
						mouthInfo = "NotSet";
					}
					logger.info("mouth is " + mouthInfo);
				}
				else if (attribute instanceof AfrDetectRightEyeAttribute && config.contains("eyemouth=yes")) {
					int status = ((AfrDetectRightEyeAttribute) attribute).getRightEye();
					String rightEyeInfo;
					if (status == AfrDetectRightEyeAttribute.Open) {
						rightEyeInfo = "Open";
					}
					else if (status == AfrDetectRightEyeAttribute.Close) {
						rightEyeInfo = "Close";
					}
					else {
						rightEyeInfo = "NotSet";
					}
					logger.info("right eye is " + rightEyeInfo);
				}
				else if (attribute instanceof AfrDetectPoseAttribute && config.contains("pose=yes")) {
					int pitch_status = ((AfrDetectPoseAttribute) attribute).getPitchPose();
					String pitchPoseInfo;
					if (pitch_status == AfrDetectPoseAttribute.PitchNormal) {
						pitchPoseInfo = "PitchNormal";
					}
					else if (pitch_status == AfrDetectPoseAttribute.PitchUp) {
						pitchPoseInfo = "PitchUp";
					}
					else if (pitch_status == AfrDetectPoseAttribute.PitchDown) {
						pitchPoseInfo = "PitchDown";
					}
					else {
						pitchPoseInfo = "PitchNotSet";
					}
					logger.info("pitch pose is " + pitchPoseInfo);
					
					int yaw_status = ((AfrDetectPoseAttribute) attribute).getYawPose();
					String yawPoseInfo;
					if (yaw_status == AfrDetectPoseAttribute.YawNormal) {
						yawPoseInfo = "YawNormal";
					}
					else if (yaw_status == AfrDetectPoseAttribute.YawLeft) {
						yawPoseInfo = "YawLeft";
					}
					else if (yaw_status == AfrDetectPoseAttribute.YawRight) {
						yawPoseInfo = "YawRight";
					}
					else {
						yawPoseInfo = "YawNotSet";
					}
					logger.info("yaw pose is " + yawPoseInfo);
				}
			}
		}
	}
	
	private static Logger logger = LoggerFactory.getLogger(HciCloudAfrHelper.class);
	

}
*/