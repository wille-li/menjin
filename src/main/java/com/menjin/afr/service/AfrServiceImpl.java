/*package com.menjin.afr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.menjin.afr.model.AccountInfo;
import com.menjin.afr.model.HciCloudAfrHelper;
import com.menjin.afr.model.HciCloudSysHelper;
import com.sinovoice.hcicloudsdk.common.HciErrorCode;

@Service
public class AfrServiceImpl implements AfrService{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	*//**
	 * 加载用户信息工具类
	 *//*
	AccountInfo mAccountInfo;


	@Override
	public boolean vertifyUser(String userID, String fileName1, String fileName2) {
		*//**
		 * 加载用户信息工具类
		 *//*
		AccountInfo mAccountInfo;

		*//**
		 * HciCloud帮助类，可完成灵云系统初始化，释放操作。
		 *//*
		HciCloudSysHelper mHciCloudSysHelper;

		*//**
		 * AFR帮助类， 可完成AFR能力的初始化，开始合成，释放操作。
		 *//*
		HciCloudAfrHelper mHciCloudAfrHelper;

		mAccountInfo = AccountInfo.getInstance();
		boolean loadResult = mAccountInfo.loadAccountInfo();
		if (loadResult) {
			// 加载信息成功进入主界面
			System.out.println("加载灵云账号成功");
		} else {
			// 加载信息失败，显示失败界面
			System.out
					.println("加载灵云账号失败！请在assets/AccountInfo.txt文件中填写正确的灵云账户信息，账户需要从www.hcicloud.com开发者社区上注册申请。");
			return false;
		}

		String capkey = mAccountInfo.getCapKey();

		mHciCloudSysHelper = HciCloudSysHelper.getInstance();
		mHciCloudAfrHelper = HciCloudAfrHelper.getInstance();

		// 必须首先调用HciCloudSys的初始化方法
		int sysInitResult = mHciCloudSysHelper.init();
		if (sysInitResult != HciErrorCode.HCI_ERR_NONE) {
			logger.info("hci init error, error code = " + sysInitResult);
			return false;
		} else {
			logger.info("hci init success");
		}
		boolean resultFlag = false;
		resultFlag = mHciCloudAfrHelper.Func(capkey, fileName1, fileName2, userID);
		// 终止 灵云 系统
		int sysReleaseRet = mHciCloudSysHelper.release();
		if (HciErrorCode.HCI_ERR_NONE != sysReleaseRet) {
			logger.info("hciRelease failed:" + sysReleaseRet);
		}
		logger.info("hciRelease Success");
		return resultFlag;
	}

}
*/